package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {
    @Test
    void Test() {
        User user = new User();
        user.setEmail("woosong@naver.com");
        user.setName("woosong");
        System.out.println("getEmail : "+ user.getEmail());
        System.out.println("getName : "+ user.getName());
        System.out.println(user);

        User user_builder = new User();
        user_builder.builder().name("john")
                .email("test@naver.com")
                //.createdAt(LocalDateTime.now())
                .build();
        System.out.println(user_builder.getName());
    }
}