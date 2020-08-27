package com.yubron.board.springboot.web;

import com.yubron.board.springboot.config.auth.LoginUser;
import com.yubron.board.springboot.config.auth.dto.SessionUser;
import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {


        if(user != null){
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        model.addAttribute("msg", "Login Please");
        model.addAttribute("url", "login");

        return "login";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model,@LoginUser SessionUser user) {
        model.addAttribute("userName",user.getName());
        return "posts-save";
    }

    @GetMapping("/posts/detail/{id}")
    public String postSearch(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("userName", user.getName());
        model.addAttribute("post", dto);

        return "posts-detail";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("userName", user.getName());
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/list")
    public String postsList(Model model,@LoginUser SessionUser user) {
        model.addAttribute("userName",user.getName());
        model.addAttribute("posts",postsService.findAllDesc());
        return "posts-list";
    }
}
