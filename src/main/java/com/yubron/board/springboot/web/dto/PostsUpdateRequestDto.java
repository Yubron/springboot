package com.yubron.board.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String imgFileUrl;
    private String title;
    private int price;
    private int count;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveToDate;

    @Builder
    public PostsUpdateRequestDto(String imgFileUrl,
                                 String title,
                                 int price,
                                 int count,
                                 String content,
                                 LocalDate effectiveToDate) {
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.effectiveToDate = effectiveToDate;
    }
}
