package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private int price;
    private int count;
    private String content;
    private String userName;
    private String userEmail;

    @Builder
    public PostsSaveRequestDto(String title, int price, int count, String content, String userName, String userEmail){
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .price(price)
                .count(count)
                .content(content)
                .userName(userName)
                .userEmail(userEmail)
                .build();
    }
}
