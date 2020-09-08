package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private int count;
    private String userName;
    private String userEmail;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.price = entity.getPrice();
        this.count = entity.getCount();
        this.userName = entity.getUserName();
        this.userEmail = entity.getUserEmail();
        this.modifiedDate = entity.getModifiedDate();
    }
}
