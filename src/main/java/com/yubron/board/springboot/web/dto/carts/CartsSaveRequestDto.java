package com.yubron.board.springboot.web.dto.carts;

import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartsSaveRequestDto {
    PostsService postsService;

    private String userEmail;
    private Long itemId;
    private String sellerEmail;
    private String sellerName;
    private String title;
    private int price;
    private int count;
    private int totalPrice;

    @Builder
    public CartsSaveRequestDto(String userEmail, Long itemId, String title, int price, int count){
        PostsResponseDto posts = postsService.findById(itemId);
        this.userEmail = userEmail;
        this.itemId = itemId;
        this.sellerEmail = posts.getUserEmail();
        this.sellerName = posts.getUserName();
        this.title = title;
        this.price = price;
        this.count = count;
        this.totalPrice = price * count;
    }

    public Carts toEntity(){
        return Carts.builder()
                .userEmail(userEmail)
                .itemId(itemId)
                .sellerEmail(sellerEmail)
                .sellerName(sellerName)
                .title(title)
                .price(price)
                .count(count)
                .build();
    }
}