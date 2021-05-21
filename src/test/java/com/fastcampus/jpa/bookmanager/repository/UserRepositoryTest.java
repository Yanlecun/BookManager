package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring context를 로딩해서 테스트에 활용하겠다는 뜻
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        userRepository.save(new User()); // 생성 후 저장
        // user라는 테이블에 있는 모든 데이터를 리스트형식으로 가져오기
        System.out.println(">>>" + userRepository.findAll());  // findAll 일반적으로 사용하지 않음
        // flush flush jpa context에서 가지고 있는 db값을 db에 반영하도록 지시하는 메소드

        //userRepository.findAll().forEach(System.out::println);

        
    }

}