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
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping({"/","/search/"})
    public String index(Model model, @LoginUser SessionUser user) {
        List<PostsResponseDto>posts = postsService.findAllDesc();
        model.addAttribute("posts", posts);

        if(user != null){
            model.addAttribute("user",user);
        }

        return "index";
    }


    @GetMapping("/search/{searchKeyword}")
    public String searchResult(Model model, @PathVariable String searchKeyword, @LoginUser SessionUser user) {

        List<PostsResponseDto> postsListResponseDtos = postsService.findAllDesc().stream()
                                                                                .filter(t->t.getTitle().contains(searchKeyword) ||
                                                                                        t.getContent().contains(searchKeyword) )
                                                                                .collect(Collectors.toList());

        model.addAttribute("posts",postsListResponseDtos);
        model.addAttribute("searchKeyword", searchKeyword);
        if(user != null){
            model.addAttribute("user",user);
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user",user);
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable Long id, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("user",user);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/detail/{id}")
    public String postsDetail(Model model, @PathVariable Long id, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        if(dto.getUserEmail().equals(user.getEmail())){
            model.addAttribute("permitModify","permitModify");
        }
        model.addAttribute("user",user);
        model.addAttribute("post", dto);

        return "posts-detail";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
