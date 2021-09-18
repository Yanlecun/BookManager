package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import lombok.Builder;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        System.out.println("findByEmailAndName" + userRepository.findByEmailAndName("woosong11@naver.com", "woosong1"));
        System.out.println("findByEmailOrName" + userRepository.findByEmailOrName("woosong11@naver.com", "woosong1"));
        System.out.println("findByCreatedAtAfter" + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter" + userRepository.findByIdAfter(4L));

        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));


        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));

        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        //System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());

        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("woosong1", "woosong2")));

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("woo"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("song1"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("song1"));

        System.out.println("findByNameLike : " + userRepository.findByNameLike("%oo%"));

    }

    @Test
    void pagingAndSorting() {
        System.out.println("findUserTop2ByNameOrderByIdDesc : " + userRepository.findUserTop2ByNameOrderByIdDesc("woosong1"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("woosong1"));

        System.out.println("findTopByNameWithSortParams : " + userRepository.findTopByName("woosong1", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));

        // toString 오버로딩 방식이 다르기 때문에 getContent를 chaining하자
        // getTotalElements = select 쿼리의 count값도 실행, 총 갯수
        System.out.println("findByNameWithPaging : " + userRepository.findByName("woosong1", PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")))).getContent());
    }
    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email")
        );
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin@fastcampus.com");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("qqqqqqqq");
        userRepository.save(user2);  // upadate
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender")); // 0 or 1 값이 반환 왜 ?
        // Enumerated의 default가 ordinal(index값)이기 때문이다. 다만 코드 리팩토링을 한다면 저장값과 index값에 괴리가 생긴다.
        // 따라서 value에 반드시 String으로 지정해주어야 한다.
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("thd@naver.com");
        user.setName("songwoo");

        userRepository.save(user);
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("songwoo1");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }
}
