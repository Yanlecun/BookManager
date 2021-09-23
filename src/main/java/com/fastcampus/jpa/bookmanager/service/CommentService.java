package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import com.fastcampus.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("리뷰 남기고 가요");

            commentRepository.save(comment);
        }
    }

    @Transactional
    public void updateSth() {
        List<Comment> comments = commentRepository.findAll();
        for(Comment comment : comments){
            comment.setComment("별로 ㅠㅠ");

            commentRepository.save(comment); // 이게 없어도 update쿼리는 진행함 ! = Dirty Check, 영속성 컨텍스트 내에 있는 기능
                                             // application.yml에서 log level을 수정해보자
        }
    }

    @Transactional(readOnly = true)  // 세션의 flush모드를 flush.manual 으로 변경 (auto가 아니므로 dirty check모드가 flush될 때 실행)
    // 실제 SimpleJpaRepository에서는 @Transactional(readonly=)임 .. save에는 별도의 @tra있고
    public void insertSth() {
        //Comment comment = new Comment(); // 새로 생성한 객체이므로 DB에 영속화 하는 과정이 없음 (dirty check x)
        Comment comment = commentRepository.findById(1L).get(); // Dirty Check 발생, 이게 Batch 로직을 수행할 경우 문제된다.
        comment.setComment("리뷰 남기기");

        commentRepository.save(comment);  // 이렇게 명시적으로 표시 해줘야 함
    }
}
