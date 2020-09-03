package com.yubron.board.springboot.domain.user;

import com.yubron.board.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "integer default 0")
    private int point;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.point = point;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, int point){
        this.name = name;
        this.point = point;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }



}
