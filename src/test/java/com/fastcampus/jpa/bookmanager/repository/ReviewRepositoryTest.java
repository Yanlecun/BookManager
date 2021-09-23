package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest() {
        //List<Review> reviews = reviewRepository.findAll();
        List<Review> reviews = reviewRepository.findAllByFetchJoin(); //N+1문제 해결
//        List<Review> reviews = reviewRepository.findAllByEntityGraph();

        reviews.forEach(System.out::println);

//        System.out.println("전체 가져옴");
//        System.out.println(reviews.get(0).getComments());
//        System.out.println("첫번째 리뷰의 코멘트들을 가져왔습니다.");  // EAGER vs LAZY
//        System.out.println(reviews.get(1).getComments());
//        System.out.println("두번째 리뷰의 코멘트들을 가져왔습니다.");
    }

}
