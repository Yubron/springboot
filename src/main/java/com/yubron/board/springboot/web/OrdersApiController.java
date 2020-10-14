package com.yubron.board.springboot.web;

import com.yubron.board.springboot.service.CartsService;
import com.yubron.board.springboot.service.OrdersService;
import com.yubron.board.springboot.web.dto.PostsSaveRequestDto;
import com.yubron.board.springboot.web.dto.orders.OrdersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersApiController {
    private final OrdersService ordersService;

    @PostMapping(value = "/api/v1/addOrder")
    public Long save(@RequestBody List<OrdersSaveRequestDto> requestDtoList) {
        Long flag = 0L;
        for(OrdersSaveRequestDto requestDto : requestDtoList) {
            OrdersSaveRequestDto ordersSaveRequestDto = OrdersSaveRequestDto.builder()
                    .userEmail(requestDto.getUserEmail())
                    .userName(requestDto.getUserName())
                    .sellerEmail(requestDto.getSellerEmail())
                    .sellerName(requestDto.getSellerName())
                    .price(requestDto.getPrice())
                    .itemId(requestDto.getItemId())
                    .count(requestDto.getCount())
                    .build();
            flag = ordersService.save(ordersSaveRequestDto);
        }
        return flag;
    }

    @PutMapping(value = "/api/v1/order/confirm/{id}")
    public void updateIsConfirm(@PathVariable Long id) {
        ordersService.updateIsConfirm(id,true);
    }

}
