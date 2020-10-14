package com.yubron.board.springboot.service;

import com.yubron.board.springboot.domain.orders.Orders;
import com.yubron.board.springboot.domain.orders.OrdersRepository;
import com.yubron.board.springboot.domain.posts.Posts;
import com.yubron.board.springboot.web.dto.orders.OrdersResponseDto;
import com.yubron.board.springboot.web.dto.orders.OrdersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public Long save(OrdersSaveRequestDto requestDto) {
        return ordersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<OrdersResponseDto> findByUserEmail(String userEmail){
        List<OrdersResponseDto> ordersResponseDtoList = ordersRepository.findByUserEmail(userEmail).stream()
                .map(OrdersResponseDto::new)
                .collect(Collectors.toList());

        return ordersResponseDtoList;
    }

    @Transactional
    public List<OrdersResponseDto> findByItemId(Long itemId){
        List<OrdersResponseDto> ordersResponseDtoList = ordersRepository.findByItemId(itemId).stream()
                .map(OrdersResponseDto::new)
                .collect(Collectors.toList());

        return ordersResponseDtoList;
    }

    @Transactional
    public void updateIsConfirm(Long id, Boolean isConfirm) {
        Orders order = ordersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("존재하지 않는 주문입니다."));

        order.updateIsConfirm(isConfirm);
    }



}
