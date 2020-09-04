package com.yubron.board.springboot.web.dto.carts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartsUpdateRequestDto {
    private int count;
    private Long itemId;

    @Builder
    public CartsUpdateRequestDto(int count) {
        this.count = count;
    }
}
