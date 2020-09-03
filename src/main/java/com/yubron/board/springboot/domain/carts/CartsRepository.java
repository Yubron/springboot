package com.yubron.board.springboot.domain.carts;

import com.yubron.board.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartsRepository extends JpaRepository<Carts, Long> {
    List<Carts> findByUserEmail(String UserEmail);
}
