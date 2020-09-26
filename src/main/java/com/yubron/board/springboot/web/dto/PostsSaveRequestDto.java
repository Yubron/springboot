package com.yubron.board.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yubron.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveFromDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveToDate;

    @Builder
    public PostsSaveRequestDto(String imgFileUrl,
                               String title,
                               int price,
                               int count,
                               String content,
                               String userName,
                               String userEmail,
                               LocalDate effectiveFromDate,
                               LocalDate effectiveToDate){
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
        this.effectiveFromDate = effectiveFromDate;
        this.effectiveToDate = effectiveToDate;
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
                .effectiveFromDate(effectiveFromDate)
                .effectiveToDate(effectiveToDate)
                .build();
    }
}
