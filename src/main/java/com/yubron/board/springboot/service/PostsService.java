package com.yubron.board.springboot.service;

import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.domain.posts.PostsRepository;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import com.yubron.board.springboot.web.dto.PostsSaveRequestDto;
import com.yubron.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getImgFileUrl(),
                requestDto.getTitle(),
                requestDto.getPrice(),
                requestDto.getCount(),
                requestDto.getContent(),
                requestDto.getEffectiveToDate());

        if(requestDto.getEffectiveToDate().isBefore(LocalDate.now())) {
            updateIneffective(id);
        } else {
            updateEffective(id);
        }
        return id;
    }

    @Transactional
    public Long updateCount(Long id, int count) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("존재하지 않는 상품입니다."));
        posts.updateCount(count);
        if(posts.getCount() < 1) {
            posts.updateIneffective(false);
        }
        return id;
    }

    @Transactional
    public Long updateIneffective(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 사용자가 없습니다. id=" + id));
        posts.updateIneffective(false);

        return id;
    }

    @Transactional
    public Long updateEffective(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 사용자가 없습니다. id=" + id));
        posts.updateIneffective(true);

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsResponseDto> findByUserEmail(String userEmail){
        return postsRepository.findByUserEmail(userEmail).stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
