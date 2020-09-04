var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });

        $('#btn-update').on('click',function(){
            _this.update();
        });

        $('#btn-delete').on('click',function(){
            _this.delete();
        });

        $('#btn-addCart').on('click',function(){
            _this.addCart('addCart');
        });

        $('#btn-purchase').on('click',function(){
            _this.addCart('purchase');
        });

        $('.cartDelete').on('click',function(){
            var id = $(this).closest('tr').data('value');
            _this.cartDelete(id);
        });

        $('.cartUpdate').on('click',function(){
            var id = $(this).closest('tr').data('value');
            _this.cartUpdate(id);
        });

        $('.shopDelete').on('click',function(){
            var id = $(this).closest('tr').data('value');
            _this.delete(id);
        });


        var selectAll = document.querySelector("#allCheck");
        selectAll.addEventListener('click', function(){
            var objs = document.querySelectorAll(".cartCheck");
            for (var i = 0; i < objs.length; i++) {
                objs[i].checked = selectAll.checked;
            };
        }, false);

        var objs = document.querySelectorAll(".cartCheck");
        for(var i=0; i<objs.length ; i++){
            objs[i].addEventListener('click', function(){
            var selectAll = document.querySelector("#allCheck");
            for (var j = 0; j < objs.length; j++) {
              if (objs[j].checked === false) {
                selectAll.checked = false;
                return;
              };
            };
            selectAll.checked = true;
        }, false);
        }

    },

    save : function() {
        var data = {
            title     : $('#title').val(),
            price     : $('#price').val(),
            count     : $('#count').val(),
            userName  : $('#userName').val(),
            userEmail : $('#userEmail').val(),
            content   : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error))
        });
    },

    update : function() {
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        var id = $('#id').val();
        $.ajax({
            type:'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 수정되었습니다");
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    delete : function(id) {
        if(arguments.length==0) {
            var id = $('#id').val();
        } else{
            var id=id;
        }

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            if(arguments.length==0) {
                window.location.href = '/';
            } else {
                window.location.href = '/user/shop';
            }
        }).fail(function(){
            alert(JSON.stringify(error));
        });

    },


    addCart : function(indicator) {
        var data = {
            userEmail : $('#userEmail').val(),
            itemId : $('#itemId').val(),
            title : $('#title').val(),
            price : $('#price').val(),
            count : $('#count').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/addCart',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if(indicator==='addCart') {
                alert("장바구니에 추가되었습니다.");
            } else {
                window.location.href = '/user/cart';
            }
        }).fail(function(xhr, status, error){
            alert(JSON.parse(xhr.responseText).message)
        });
    },

    cartDelete : function(id) {
        var id = $('#id'+id).val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/carts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function(){
            alert('장바구니가 삭제되었습니다.');
            window.location.href = '/user/cart';
        }).fail(function(){
            alert(JSON.stringify(error));
        });
    },

    cartUpdate : function(id) {
        var data = {
            id : $('#id'+id).val(),
            itemId : $('#itemId'+id).val(),
            count : $('#count'+id).val()
        };

        $.ajax({
            type:'PUT',
            url: '/api/v1/carts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("장바구니가 수정되었습니다");
            window.location.href = '/user/cart';
        }).fail(function(xhr, status, error){
            alert(JSON.parse(xhr.responseText).message)
        });

    },


    // 공통함수
    gfn_toast : function(option){
      if(!!$("#g_toast_container")){
        $("#g_toast_container").remove();
      }
      var optionDefault = {"title": "information", "contents": "successful"}
      option = $.extend(optionDefault, option);
      //console.log(option);
      var toastDiv='';
      toastDiv += '<div id="g_toast_container" style="min-height: 250px;position:absolute;top:600px;">';
      toastDiv += '<div id="g_toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">';
      toastDiv += '  <div class="toast-header bg-info text-white">';
      toastDiv += '    <i class="fas fa-bell"></i>';
      toastDiv += '    <strong class="mr-auto ml-3">' + option.title + '</strong>';
      toastDiv += '    <small class="text-muted ml-3">2 seconds ago</small>';
      toastDiv += '    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">';
      toastDiv += '      <span aria-hidden="true">&times;</span>';
      toastDiv += '    </button>';
      toastDiv += '  </div>';
      toastDiv += '  <div class="toast-body">' + option.contents + '</div>';
      toastDiv += '</div>';
      toastDiv += '</div>';

      $("body").append(toastDiv);
      // 좌우 중앙에 토스트 윈도우를 위치 시킴
      $("#g_toast_container").css({"left": ((($(window).width() - $("#g_toast_container").outerWidth()) / 2) + $(window).scrollLeft() + "px")});
      // 디폴트 옵션에서 delay만 2000 (delay의 디폴트는 500)
      $("#g_toast").toast({"animation": true, "autohide": true, "delay": 1000});
      $("#g_toast").toast('show');
    }


};

main.init();