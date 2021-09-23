package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Test
    void commentTest() {
        commentService.init();

        commentService.updateSth(); // entity로 update를 하면 전체 컬럼에 대한 업데이트를 진행함
        // @DynamicUpdate를 해주면 해당 부분만 찾아서 간단히 업데이트
        commentService.insertSth();
    }
}