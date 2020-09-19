package com.yubron.board.springboot.web;

import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.service.S3Service;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import com.yubron.board.springboot.web.dto.PostsSaveRequestDto;
import com.yubron.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    private final S3Service s3Service;



    @PostMapping("/api/v1/posts")
    public Long save(@RequestParam("title") String title,
                     @RequestParam("price") int price,
                     @RequestParam("content") String content,
                     @RequestParam("count") int count,
                     @RequestParam("imgFile") MultipartFile imgFile,
                     @RequestParam("userName") String userName,
                     @RequestParam("userEmail") String userEmail) throws IOException {

        String imgFileUrl = s3Service.upload(imgFile);

        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                                                                    .imgFileUrl(imgFileUrl)
                                                                    .title(title)
                                                                    .price(price)
                                                                    .content(content)
                                                                    .count(count)
                                                                    .userName(userName)
                                                                    .userEmail(userEmail)
                                                                    .build();

        return postsService.save(postsSaveRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestParam("title") String title,
                                              @RequestParam("price") int price,
                                              @RequestParam("content") String content,
                                              @RequestParam("count") int count,
                                              @RequestParam("imgFile") MultipartFile imgFile) throws IOException {

        String imgFileUrl;
        String originalImgFileUrl = postsService.findById(id).getImgFileUrl();

        if(imgFile.getOriginalFilename().equals("")) {
            imgFileUrl = originalImgFileUrl;
        } else {
            imgFileUrl = s3Service.upload(imgFile);
        }

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                                                                        .imgFileUrl(imgFileUrl)
                                                                        .title(title)
                                                                        .price(price)
                                                                        .content(content)
                                                                        .count(count)
                                                                        .build();
        return postsService.update(id, postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
