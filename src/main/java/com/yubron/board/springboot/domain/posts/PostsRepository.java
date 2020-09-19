package com.yubron.board.springboot.domain.posts;

import com.yubron.board.springboot.domain.carts.Carts;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    List<Posts> findByUserEmail(String UserEmail);
}
