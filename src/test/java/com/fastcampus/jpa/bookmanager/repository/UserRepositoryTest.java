package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import lombok.Builder;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest // spring context를 로딩해서 테스트에 활용하겠다는 뜻
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    //@Transactional
    void crud() {
        //userRepository.save(new User("kim", "fa@naver.com"));
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("asda@naver.com");
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void select() {
        System.out.println(userRepository.findByName("woosong"));
        // 모두 동일한 쿼리, 동일한 결괏값을 가짐
        // 잘못 전달될만한 네이밍 ex) findSomethingByEmail 같은 거 쓰면 안 된다.
        // 잘못된 메소드이름은 실재 동작될 때 오류난다.
        System.out.println("findByEmail" + userRepository.findByEmail("woosong@naver.com"));
        System.out.println("getByEmail" + userRepository.getByEmail("woosong@naver.com"));
        System.out.println("readByEmail" + userRepository.readByEmail("woosong@naver.com"));
        System.out.println("queryByEmail" + userRepository.queryByEmail("woosong@naver.com"));
        System.out.println("searchByEmail" + userRepository.searchByEmail("woosong@naver.com"));
        System.out.println("streamByEmail" + userRepository.streamByEmail("woosong@naver.com"));
        System.out.println("findUserByEmail" + userRepository.findUserByEmail("woosong@naver.com"));

        System.out.println("findTop1ByName" + userRepository.findTop1ByName("woosong@naver.com"));
        System.out.println("findFirst1ByName" + userRepository.findFirst1ByName("woosong@naver.com"));

    }
}
