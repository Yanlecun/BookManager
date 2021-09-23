package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    void commentTest() {
        Comment comment = commentRepository.findById(3L).get();
        comment.setCommentedAt(LocalDateTime.now());
        commentRepository.saveAndFlush(comment); // flush는 영속화 컨텍스트에 넣어줄 뿐, DB에 직접 접근해서 하는 친구가 아님
        System.out.println(commentRepository.findById(3L).get()); // 트잭 x :자바 버전 now , o : sql 버전 now
//        commentRepository.flush();
//        System.out.println(commentRepository.findById(3L).get());
//        entityManager.flush();
//        System.out.println(commentRepository.findById(3L).get());
        entityManager.clear();
        System.out.println(commentRepository.findById(3L).get());  // save @트잭 x : 자바 버전 now, o : null
                                                                   // saveAndFlush @트잭 x,o : 자바 버전 now
    }

}