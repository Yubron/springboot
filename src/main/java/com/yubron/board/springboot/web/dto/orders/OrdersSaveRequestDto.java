package com.yubron.board.springboot.web.dto.orders;

import com.yubron.board.springboot.domain.orders.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdersSaveRequestDto {
    private String userEmail;
    private String userName;
    private String sellerEmail;
    private String sellerName;
    private Long itemId;
    private int price;
    private int count;
    private int totalPrice;

    @Builder
    public OrdersSaveRequestDto(String userEmail, String userName,String sellerEmail, String sellerName, Long itemId, int price, int count) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.itemId = itemId;
        this.price = price;
        this.count = count;
        this.totalPrice = price * count;
    }

    public Orders toEntity(){
        return Orders.builder()
                .userEmail(userEmail)
                .userName(userName)
                .sellerEmail(sellerEmail)
                .sellerName(sellerName)
                .itemId(itemId)
                .price(price)
                .count(count)
                .totalPrice(totalPrice)
                .build();
    }
}

