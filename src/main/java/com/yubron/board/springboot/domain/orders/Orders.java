package com.yubron.board.springboot.domain.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userEmail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sellerEmail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sellerName;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private int price;

    private int count;

    private int totalPrice;

    private Boolean isConfirm;

    @Builder
    public Orders(String userEmail, String userName, String sellerEmail, String sellerName, Long itemId, int price, int count, int totalPrice) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.itemId = itemId;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
        this.isConfirm = false;
    }

    public void updateIsConfirm(boolean isConfirm) {
        this.isConfirm = isConfirm;
    }
}
