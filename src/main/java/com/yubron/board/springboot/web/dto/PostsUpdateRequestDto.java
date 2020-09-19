package com.yubron.board.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String imgFileUrl;
    private String title;
    private int price;
    private int count;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String imgFileUrl, String title, int price, int count, String content) {
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
    }
}
