package com.yubron.board.springboot.domain.carts;

import com.yubron.board.springboot.domain.BaseTimeEntity;
import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Carts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userEmail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sellerEmail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sellerName;

    @Column(nullable = false)
    private Long itemId;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    private int count;

    private int totalPrice;



    @Builder
    public Carts(String userEmail, Long itemId, String sellerEmail, String sellerName, String title, int price, int count){
        this.userEmail = userEmail;
        this.itemId = itemId;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.title = title;
        this.price = price;
        this.count = count;
        this.totalPrice = price * count;
    }

    public void update(int count) {
        this.count = count;
        this.totalPrice = count * this.price;
    }
}
