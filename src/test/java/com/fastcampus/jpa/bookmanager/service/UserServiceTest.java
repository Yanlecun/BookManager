package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        userService.put();  // new@naver.com을 가진 User
        // 단순히 Java 객체로만 존재하다 Garabage Collector에 의해 사라지는 데이트 = 비영속상태

        //System.out.println(" >>>" + userRepository.findByEmail("new@naver.com"));

        userRepository.findAll().forEach(System.out::println);
    }

}