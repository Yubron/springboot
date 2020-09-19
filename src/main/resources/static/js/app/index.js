var main = {
    init : function() {
        var _this = this;

        $('#btn-search').on('click', function(){
            _this.search();
        });

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


        /*
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
        */
    },
    search : function() {
        var data = {
            searchKeyword : $('#searchKeyword').val()
        };

        location.href='/search/'+data.searchKeyword;
    },
    save : function() {
        var form = $('#frm-upload')[0];
        var data = new FormData(form);
        data.append("userName", $('#userName').val());
        data.append("userEmail", $('#userEmail').val());
        var imgFile = $('#imgFile').val();
        if(imgFile==="") {
            alert("사진을 넣어주세요");
            return 0;
        }

        if (formValidation()===true) {
            $.ajax({
                url: '/api/v1/posts',
                type: 'POST',
                enctype: 'multipart/form-data',
                data: data,
                processData: false,
                contentType: false,
                cache: false
            }).done(function() {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function(error){
                alert(JSON.stringify(error))
            });
        } else{
            alert("필수값을 입력하세요.")
        }

    },

    update : function() {
        var id = $('#id').val();

        var form = $('#frm-upload')[0];
        var data = new FormData(form);
        var imgFile = $('#imgFile').val();

        if (formValidation() === true) {
            $.ajax({
                type:'PUT',
                url: '/api/v1/posts/'+id,
                enctype: 'multipart/form-data',
                data: data,
                processData: false,
                contentType: false,
                cache: false
            }).done(function(){
                alert("글이 수정되었습니다");
                window.location.href = '/';
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        } else{
              alert("필수값을 입력하세요.")
          }
    },

    delete : function(id) {
        var id = (arguments.length==0) ? $('#id').val() : id;

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




};
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var width = document.getElementsByClassName("input-group-prepend")[0].clientWidth - 80;
            $('#blah').attr('src', e.target.result);
            $('#blah').attr('style', 'width:400'+ 'px; display:block; max-height: 250px;');
        }
        reader.readAsDataURL(input.files[0]);
        var fileName = document.getElementById('imgFile').files[0].name;
        $('#imgFileLabel').text( fileName );
    }
}


main.init();

