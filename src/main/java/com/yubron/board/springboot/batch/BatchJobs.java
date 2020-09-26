package com.yubron.board.springboot.batch;

import com.yubron.board.springboot.service.PostsService;
import com.yubron.board.springboot.web.dto.PostsResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BatchJobs {

    Logger logger = LoggerFactory.getLogger(BatchJobs.class);

    @Autowired
    private PostsService postsService;

    @Scheduled(cron = "1 0 0 * * *")
    public void batchRefresh(){

        List<PostsResponseDto> postsResponseDtoList;

        LocalDate today = LocalDate.now();

        postsResponseDtoList = postsService.findAllDesc().stream()
                                .filter(t->t.getIsEffective().compareTo(true)==0)
                                .filter(t->t.getEffectiveToDate().isBefore(today))
                                .collect(Collectors.toList());

        if(postsResponseDtoList.isEmpty()) {
            logger.info("nothing to do");
        } else{
            logger.info("do it");
            postsResponseDtoList.forEach(t->postsService.updateIneffective(t.getId()));
        }
    }
}



