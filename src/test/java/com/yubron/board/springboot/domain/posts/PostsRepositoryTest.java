package com.yubron.board.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "test title";
        String content = "test content";

        postsRepository.save(Posts.builder()
                                .title(title)
                                .imgFileUrl("imgFileUrl")
                                .content(content)
                                .count(5)
                                .price(500)
                                .userName("yoo jin soo")
                                .userEmail("yubron@gmail.com")
                                .effectiveFromDate(LocalDate.now())
                                .effectiveToDate(LocalDate.now())
                                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,6,28,0,0,0);
        postsRepository.save(Posts.builder()
                                .title("title")
                                .imgFileUrl("imgFileUrl")
                                .count(5)
                                .price(5)
                                .content("content")
                                .userName("yoo jin soo")
                                .userEmail("yubron@gmail.com")
                                .effectiveFromDate(LocalDate.now())
                                .effectiveToDate(LocalDate.now())
                                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate = " + posts.getCreatedDate() +
                        "modified Date = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
