package com.yubron.board.springboot.service;

import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.carts.CartsRepository;
import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.domain.posts.PostsRepository;
import com.yubron.board.springboot.domain.user.User;
import com.yubron.board.springboot.web.dto.PostsUpdateRequestDto;
import com.yubron.board.springboot.web.dto.carts.CartsResponseDto;
import com.yubron.board.springboot.web.dto.carts.CartsSaveRequestDto;
import com.yubron.board.springboot.web.dto.carts.CartsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartsService {
    private final CartsRepository cartsRepository;

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(CartsSaveRequestDto requestDto) throws Exception{
//        if (this.findByUserEmail(requestDto.getUserEmail())
//                .stream()
//                .filter(t -> t.getItemId() == requestDto.getItemId())
//                .count() != 0) {
//
//            throw new Exception("장바구니에 이미 담겨져있는 상품입니다.");
//        }
//
//        if (requestDto.getUserEmail().equals(postsRepository.findById(requestDto.getItemId()).get().getUserEmail())) {
//            throw new Exception("본인의 물품은 장바구니에 담을 수 없습니다.");
//        }
//
//        if (requestDto.getCount() > postsRepository.findById(requestDto.getItemId()).get().getCount()) {
//            throw new Exception("상품의 수량이 초과했습니다.");
//        }

        return cartsRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public List<CartsResponseDto> findByUserEmail(String userEmail){
        List<CartsResponseDto> cartsResponseDtoList = cartsRepository.findByUserEmail(userEmail).stream()
                .map(CartsResponseDto::new)
                .collect(Collectors.toList());
        cartsResponseDtoList.stream().forEach(item -> {
            item.setImgFileUrl(postsRepository.findById(item.getItemId()).get().getImgFileUrl());
        });
        return cartsResponseDtoList;
    }

    @Transactional
    public Long update(Long id, CartsUpdateRequestDto requestDto) throws Exception {
        Carts carts = cartsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 장바구니가 없습니다. id=" + id));

        if (requestDto.getCount() > postsRepository.findById(requestDto.getItemId()).get().getCount()) {
            throw new Exception("상품의 수량이 초과했습니다.");
        }

        carts.update(requestDto.getCount());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Carts carts = cartsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 장바구니 목록이 없습니다. id=" + id));

        cartsRepository.delete(carts);
    }
}
