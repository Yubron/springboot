package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.service.S3Service;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;


@Getter
public class PostsResponseDto {

    private Long id;
    private String imgFileUrl;
    private String title;
    private int price;
    private int count;
    private String content;
    private String userName;
    private String userEmail;
    private LocalDateTime modifiedDate;

    public PostsResponseDto(Posts entity){

        this.id = entity.getId();
        this.imgFileUrl = entity.getImgFileUrl();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.count = entity.getCount();
        this.content = entity.getContent();
        this.userName = entity.getUserName();
        this.userEmail = entity.getUserEmail();
        this.modifiedDate = entity.getModifiedDate();
    }
}
