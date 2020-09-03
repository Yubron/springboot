package com.yubron.board.springboot.config.auth.dto;

import com.yubron.board.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private int point;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.picture = user.getPicture();
    }
}
