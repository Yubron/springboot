package com.yubron.board.springboot.web.dto.carts;

import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartsSaveRequestDto {
    private String userEmail;
    private Long itemId;
    private String title;
    private int price;
    private int count;
    private int totalPrice;

    @Builder
    public CartsSaveRequestDto(String userEmail, Long itemId, String title, int price, int count, String content, String userName){
        this.userEmail = userEmail;
        this.itemId = itemId;
        this.title = title;
        this.price = price;
        this.count = count;
        this.totalPrice = price * count;

    }

    public Carts toEntity(){
        return Carts.builder()
                .userEmail(userEmail)
                .itemId(itemId)
                .title(title)
                .price(price)
                .count(count)
                .build();
    }
}