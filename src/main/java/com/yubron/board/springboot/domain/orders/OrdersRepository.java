package com.yubron.board.springboot.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserEmail(String UserEmail);
    List<Orders> findByItemId(Long ItemId);
}
