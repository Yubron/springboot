package com.yubron.board.springboot.web;

import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.service.S3Service;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import com.yubron.board.springboot.web.dto.PostsSaveRequestDto;
import com.yubron.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    private final S3Service s3Service;

    @PostMapping(value = "/api/v1/posts" , consumes = {"multipart/form-data"})
    public Long save(@RequestPart("requestDto") PostsSaveRequestDto requestDto,
                     @RequestPart(value = "imgFile") MultipartFile imgFile) throws IOException{

        String imgFileUrl = s3Service.upload(imgFile);

        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .imgFileUrl(imgFileUrl)
                .title(requestDto.getTitle())
                .price(requestDto.getPrice())
                .content(requestDto.getContent())
                .count(requestDto.getCount())
                .userName(requestDto.getUserName())
                .userEmail(requestDto.getUserEmail())
                .effectiveToDate(requestDto.getEffectiveToDate())
                .effectiveFromDate(requestDto.getEffectiveFromDate())
                .build();

        return postsService.save(postsSaveRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestPart("requestDto") PostsUpdateRequestDto requestDto,
                                              @RequestPart(value="imgFile", required = false) MultipartFile imgFile) throws IOException {

        String imgFileUrl;
        String originalImgFileUrl = postsService.findById(id).getImgFileUrl();

        if(imgFile == null) {
            imgFileUrl = originalImgFileUrl;
        } else {
            imgFileUrl = s3Service.upload(imgFile);
        }

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                                                                        .imgFileUrl(imgFileUrl)
                                                                        .title(requestDto.getTitle())
                                                                        .price(requestDto.getPrice())
                                                                        .content(requestDto.getContent())
                                                                        .count(requestDto.getCount())
                                                                        .effectiveToDate(requestDto.getEffectiveToDate())
                                                                        .build();
        return postsService.update(id, postsUpdateRequestDto);
    }

    @PutMapping("/api/v1/posts/count/{id}")
    public Long updateCount(@PathVariable Long id, @RequestParam("count") int count) {
        return postsService.updateCount(id,count);
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
