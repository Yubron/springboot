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

        $('#btn-order').on('click',function(){
            _this.addCart('order');
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

        $('#btn-doOrder').on('click',function() {
            _this.doOrder();
        });

        $('.orderConfirm').on('click',function() {
            var id = $(this).closest('tr').data('value');
            _this.orderConfirm(id);
        });
    },
    search : function() {
        var data = {
            searchKeyword : $('#searchKeyword').val()
        };

        location.href='/search/'+data.searchKeyword;
    },
    save : function() {
        var formData = new FormData();
        var file = $('input[type=file]')[0].files[0];
        formData.append('requestDto', new Blob([JSON.stringify({
                        'title' : $('#title').val(),
                        'price' : $('#price').val(),
                        'content' : $('#content').val(),
                        'count' : $('#count').val(),
                        'userName' : $('#userName').val(),
                        'userEmail' : $('#userEmail').val(),
                        'effectiveToDate' : $('#effectiveToDate').val(),
                        'effectiveFromDate' : $('#effectiveFromDate').val()
                    })], {
                        type: "application/json"
                    }));

        formData.append("imgFile", file);


        if (formValidation()===true) {
            $.ajax({
                url: '/api/v1/posts',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false
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

        var formData = new FormData();
        var file = $('input[type=file]')[0].files[0];
        formData.append('requestDto', new Blob([JSON.stringify({
                        'title' : $('#title').val(),
                        'price' : $('#price').val(),
                        'content' : $('#content').val(),
                        'count' : $('#count').val(),
                        'effectiveToDate' : $('#effectiveToDate').val()
                    })], {
                        type: "application/json"
                    }));

        formData.append("imgFile", file);

        if (formValidation() === true) {
            $.ajax({
                url: '/api/v1/posts/'+id,
                type:'PUT',
                data: formData,
                processData: false,
                contentType: false
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
            sellerEmail : $('#sellerEmail').val(),
            sellerName : $('#sellerName').val(),
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

    orderConfirm : function(id) {

        $.ajax({
            type:'PUT',
            url: '/api/v1/order/confirm/'+id,
        }).done(function(){
            alert("구매가 확정 되었습니다 !");
            window.location.href = '/user/orderList';
        }).fail(function(xhr, status, error){
            alert(JSON.parse(xhr.responseText).message)
        });
    },

    doOrder : function() {
        var orderList = [];
        var amount = 0;
        $('.cartItem').each(function(index) {
            if($(this).find('.cartCheck').is(":checked")) {
                amount += Number($(this).find('.totalPrice').text().split('원')[0]);
                var orderItem = {
                    userEmail : $('#userEmail').val(),
                    userName: $('#userName').val(),
                    sellerEmail : $(this).find('#sellerEmail').val(),
                    sellerName: $(this).find('#sellerName').val(),
                    itemId : $(this).find('.itemId').val(),
                    price : $(this).find('.price').text().split('원')[0],
                    count : $(this).find('.count').val(),
                    cartId : $(this).data("value")
                };
                orderList.push(orderItem);
            }
        });

        var IMP = window.IMP;
        IMP.init('imp31853069');

        IMP.request_pay({
            pg: 'kakaopay',
            pay_method:'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: '번개장터',
            amount: amount,
            buyer_email: $('#userEmail').val(),
            buyer_name: $('#userName').val()
        }, function (rsp) {
            console.log('test');
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '\n결제 금액 : ' + rsp.paid_amount;
                $.ajax({
                    type: 'POST',
                    url: '/api/v1/addOrder',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(orderList)
                }).done(function() {
                    alert("구매 완료 되었습니다.");
                }).fail(function(error){
                    alert(JSON.stringify(error));
                });

                orderList.forEach(function(order) {
                    $.ajax({
                        type: 'DELETE',
                        url : '/api/v1/carts/'+order.cartId,
                        dataType : 'json',
                        contentType : 'application/json; charset=utf-8'
                    }).done(function() {
                    }).fail(function(error) {
                        alert(JSON.stringify(error));
                    });

                    $.ajax({
                        type: 'PUT',
                        url : '/api/v1/posts/count/'+order.itemId,
                        data : { count : order.count },

                    }).done(function() {
                    }).fail(function(error) {
                        alert(JSON.stringify(error));
                    });
                });
            } else {
                var msg = '결제에 실패하였습니다.\n';
                msg += '내용 : ' + rsp.error_msg;
            }
            alert(msg);
            window.location.href = '/user/cart';
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

