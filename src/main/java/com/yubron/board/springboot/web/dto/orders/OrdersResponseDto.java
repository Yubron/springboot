package com.yubron.board.springboot.web.dto.orders;

import com.yubron.board.springboot.domain.orders.Orders;
import com.yubron.board.springboot.domain.posts.PostsRepository;
import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersResponseDto {

    private Long id;
    private String userEmail;
    private String userName;
    private String sellerEmail;
    private String sellerName;
    private Long itemId;
    private int price;
    private int count;
    private int totalPrice;
    private Boolean isConfirm;
    private String title;
    private String imgFileUrl;

    public OrdersResponseDto(Orders ordersEntity) {
        this.id = ordersEntity.getId();
        this.userEmail = ordersEntity.getUserEmail();
        this.userName = ordersEntity.getUserName();
        this.sellerEmail = ordersEntity.getSellerEmail();
        this.sellerName = ordersEntity.getSellerName();
        this.itemId = ordersEntity.getItemId();
        this.price = ordersEntity.getPrice();
        this.count = ordersEntity.getCount();
        this.totalPrice = ordersEntity.getTotalPrice();
        this.isConfirm = ordersEntity.getIsConfirm();


    }
}
