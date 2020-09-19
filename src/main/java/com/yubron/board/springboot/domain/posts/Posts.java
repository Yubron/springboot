package com.yubron.board.springboot.domain.posts;

import com.yubron.board.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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



    @Builder
    public Posts(String imgFileUrl, String title, int price, int count, String content, String userName, String userEmail){
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public void update(String imgFileUrl, String title, int price, int count, String content) {
        this.imgFileUrl = imgFileUrl;
        this.title = title;
        this.price = price;
        this.count = count;
        this.content = content;
    }

}
