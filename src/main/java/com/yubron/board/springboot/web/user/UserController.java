package com.yubron.board.springboot.web.user;

import com.yubron.board.springboot.config.auth.LoginUser;
import com.yubron.board.springboot.config.auth.dto.SessionUser;
import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.service.CartsService;
import com.yubron.board.springboot.service.OrdersService;
import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import com.yubron.board.springboot.web.dto.carts.CartsResponseDto;
import com.yubron.board.springboot.web.dto.orders.OrdersResponseDto;
import com.yubron.board.springboot.web.dto.shop.ShopResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final CartsService cartsService;

    private final PostsService postsService;

    private final OrdersService ordersService;
    @GetMapping("/user/profile")
    public String profile(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user",user);
        return "user-profile";
    }

    @GetMapping("/user/cart")
    public String cart(Model model, @LoginUser SessionUser user) {
        List<CartsResponseDto> carts = cartsService.findByUserEmail(user.getEmail()).stream()
                .filter(t->postsService.findById(t.getItemId()).getIsEffective().compareTo(true)==0)
                .collect(Collectors.toList());
        model.addAttribute("carts",carts);
        model.addAttribute("cartsIsEmpty",carts.isEmpty());
        model.addAttribute("user",user);
        return "user-cart";
    }

    @GetMapping("/user/orderList")
    public String orderList(Model model, @LoginUser SessionUser user) {
        List<OrdersResponseDto> orders = ordersService.findByUserEmail(user.getEmail())
                                                        .stream()
                                                        .filter(t->t.getIsConfirm()==false)
                                                        .collect(Collectors.toList());
        for(OrdersResponseDto order : orders) {
            PostsResponseDto posts = postsService.findById(order.getItemId());
            order.setTitle(posts.getTitle());
            order.setImgFileUrl(posts.getImgFileUrl());
        }
        model.addAttribute("orders", orders);
        model.addAttribute("orderList",orders.isEmpty());
        model.addAttribute("user",user);
        return "user-orderList";
    }

    @GetMapping("/user/shop")
    public String shop(Model model, @LoginUser SessionUser user) {
        List<ShopResponseDto> shops = new ArrayList<>();
        List<PostsResponseDto> posts  = postsService.findByUserEmail(user.getEmail());

        posts.forEach( post -> shops.add( new ShopResponseDto(post,ordersService.findByItemId(post.getId()).stream()
                                                                                                           .filter(order->order.getIsConfirm() == false)
                                                                                                           .collect(Collectors.toList()) ) ));

        model.addAttribute("shops", shops);
        model.addAttribute("shopsIsEmpty", shops.isEmpty());
        model.addAttribute("user",user);
        return "user-shop";
    }
}
