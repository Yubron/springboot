package com.yubron.board.springboot.web.dto.shop;


import com.yubron.board.springboot.web.dto.PostsResponseDto;
import com.yubron.board.springboot.web.dto.orders.OrdersResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopResponseDto {
    private PostsResponseDto post;
    private List<OrdersResponseDto> orders;

    @Builder
    public ShopResponseDto(PostsResponseDto post, List<OrdersResponseDto> orders) {
        this.post = post;
        this.orders = orders;
    }
}
