package com.yubron.board.springboot.web.user;

import com.yubron.board.springboot.config.auth.LoginUser;
import com.yubron.board.springboot.config.auth.dto.SessionUser;
import com.yubron.board.springboot.service.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final CartsService cartsService;

    @GetMapping("/user/profile")
    public String profile(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user",user);
        return "user-profile";
    }

    @GetMapping("/user/cart")
    public String cart(Model model, @LoginUser SessionUser user) {
        model.addAttribute("carts",cartsService.findByUserEmail(user.getEmail()));
        model.addAttribute("user",user);
        return "user-cart";
    }

    @GetMapping("/user/shop")
    public String shop(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user",user);
        return "user-shop";
    }
}
