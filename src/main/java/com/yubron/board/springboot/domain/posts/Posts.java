package com.yubron.board.springboot.domain.posts;

import com.yubron.board.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String imgFileUrl;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    private int count;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String userName;

    private String userEmail;


    @Column(nullable = false)
    private LocalDate effectiveFromDate;

    private LocalDate effectiveToDate;

    private Boolean isEffective;

    @Builder
    public Posts(String imgFileUrl, String title, int price, int count, String content, String userName, String userEmail,LocalDate effectiveFromDate, LocalDate effectiveToDate){
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
        this.effectiveFromDate = effectiveFromDate;
        this.effectiveToDate = effectiveToDate;
        this.isEffective = true;

    }

    public void update(String imgFileUrl, String title, int price, int count, String content, LocalDate effectiveToDate) {
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.effectiveToDate = effectiveToDate;
    }

    public void updateIneffective(boolean isEffective) {
        this.isEffective = isEffective;
    }

    public void updateCount(int count) { this.count = this.count - count; }
}
