package com.yubron.board.springboot.web.dto;

import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String imgFileUrl;
    private String title;
    private int price;
    private int count;
    private String content;
    private String userName;
    private String userEmail;

    @Builder
    public PostsSaveRequestDto(String imgFileUrl, String title, int price, int count, String content, String userName, String userEmail){
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Posts toEntity(){
        return Posts.builder()
                .imgFileUrl(imgFileUrl)
                .title(title)
                .price(price)
                .count(count)
                .content(content)
                .userName(userName)
                .userEmail(userEmail)
                .build();
    }
}
