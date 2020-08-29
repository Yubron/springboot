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
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, int price, int count, String content, String author){
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .price(price)
                .count(count)
                .content(content)
                .author(author)
                .build();
    }
}
