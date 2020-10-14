package com.yubron.board.springboot.web.dto.carts;

import com.yubron.board.springboot.domain.carts.Carts;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartsResponseDto {
    private Long id;
    private String userEmail;
    private Long itemId;
    private String title;
    private int price;
    private int count;
    private int totalPrice;
    private LocalDateTime modifiedDate;
    private String imgFileUrl;

    private String sellerEmail;
    private String sellerName;

    public CartsResponseDto(Carts entity){

        this.id = entity.getId();
        this.userEmail = entity.getUserEmail();
        this.itemId = entity.getItemId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.count = entity.getCount();
        this.totalPrice = entity.getTotalPrice();
        this.modifiedDate = entity.getModifiedDate();
        this.sellerEmail = entity.getSellerEmail();
        this.sellerName = entity.getSellerName();
    }
}
