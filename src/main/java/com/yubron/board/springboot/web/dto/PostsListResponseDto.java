package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private int price;
    private int count;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.count = entity.getCount();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
