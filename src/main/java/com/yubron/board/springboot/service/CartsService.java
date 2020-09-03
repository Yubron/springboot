package com.yubron.board.springboot.service;

import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.domain.carts.CartsRepository;
import com.yubron.board.springboot.domain.user.User;
import com.yubron.board.springboot.web.dto.carts.CartsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartsService {
    private final CartsRepository cartsRepository;

    @Transactional
    public Long save(CartsSaveRequestDto requestDto) {
        return cartsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<Carts> findByUserEmail(String userEmail){
        return cartsRepository.findByUserEmail(userEmail);


    }
}
