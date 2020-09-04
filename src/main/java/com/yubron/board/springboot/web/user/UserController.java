package com.yubron.board.springboot.web.user;

import com.yubron.board.springboot.config.auth.LoginUser;
import com.yubron.board.springboot.config.auth.dto.SessionUser;
import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.service.CartsService;
import com.yubron.board.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final CartsService cartsService;

    private final PostsService postsService;

    @GetMapping("/user/profile")
    public String profile(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user",user);
        return "user-profile";
    }

    @GetMapping("/user/cart")
    public String cart(Model model, @LoginUser SessionUser user) {
        List<Carts> carts = cartsService.findByUserEmail(user.getEmail());
        model.addAttribute("carts",carts);
        model.addAttribute("cartsIsEmpty",carts.isEmpty());
        model.addAttribute("user",user);
        return "user-cart";
    }

    @GetMapping("/user/shop")
    public String shop(Model model, @LoginUser SessionUser user) {
        List<Posts> posts = postsService.findByUserEmail(user.getEmail());
        model.addAttribute("posts", posts);
        model.addAttribute("postsIsEmpty", posts.isEmpty());
        model.addAttribute("user",user);
        return "user-shop";
    }
}
