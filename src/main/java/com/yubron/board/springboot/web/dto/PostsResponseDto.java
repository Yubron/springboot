package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private int price;
    private int count;
    private String content;
    private String userName;
    private String userEmail;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.count = entity.getCount();
        this.content = entity.getContent();
        this.userName = entity.getUserName();
        this.userEmail = entity.getUserEmail();
    }
}
