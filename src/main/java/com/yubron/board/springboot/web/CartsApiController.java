package com.yubron.board.springboot.web;

import com.yubron.board.springboot.service.CartsService;
import com.yubron.board.springboot.web.dto.carts.CartsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartsApiController {

    private final CartsService cartsService;

    @PostMapping("/api/v1/addCart")
    public Long save(@RequestBody CartsSaveRequestDto requestDto){
        return cartsService.save(requestDto);
    }
}
