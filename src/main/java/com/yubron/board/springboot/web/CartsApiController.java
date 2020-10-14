package com.yubron.board.springboot.web;

import com.yubron.board.springboot.service.CartsService;
import com.yubron.board.springboot.web.dto.carts.CartsSaveRequestDto;
import com.yubron.board.springboot.web.dto.carts.CartsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartsApiController {

    private final CartsService cartsService;

    @PostMapping("/api/v1/addCart")
    public Long save(@RequestBody CartsSaveRequestDto requestDto) throws Exception {

        Long id = 0L;
        try {
            id = cartsService.save(requestDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return id;
    }

    @PutMapping("/api/v1/carts/{id}")
    public Long update(@PathVariable Long id, @RequestBody CartsUpdateRequestDto requestDto) throws Exception {
        Long cartId = 0L;
        try {
            cartId = cartsService.update(id, requestDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return cartId;
    }

    @DeleteMapping("/api/v1/carts/{id}")
    public Long delete(@PathVariable Long id){
        cartsService.delete(id);
        return id;
    }

}
