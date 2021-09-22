package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import lombok.Builder;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private EntityManager entityManager;

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
        //user.setGender(Gender.MALE);
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

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("thd-new@naver.com");
        user.setName("thd-new");

        userRepository.save(user);

        user.setName("thd-newnew");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("thddn");
        user.setEmail("abcd@naver.com");
        //user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("woosong");
        userRepository.save(user);

        user.setEmail("a@naver.com");
        userRepository.save(user);

        //userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("a@naver.com").getId()
//        );

        List<UserHistory> result = userRepository.findByEmail("a@naver.com").getUserHistories();
        result.forEach(System.out::println);
    }

    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("setve");
        user.setHomeAddress(new Address("서울시", "강남구", "빌딩 101호", "10254"));
        user.setCompanyAddress(new Address("서울시", "성동구", "성수빌딩 202호", "21111"));

        User user1 = new User();
        user1.setName("시민1");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        User user2 = new User();
        user2.setName("시민2");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address()); // 어떤 방식으로 진행되었길래 결과가 다를까 ?

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);

        entityManager.clear(); // 이렇게 해주면 embed된 객체가 null인 경우 그 응용 칼럼 모두 null값이 된다.. 그렇구나 정도로 알자

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println); // UserEntityListener 변경후 잘 저장되었는지 확인
        // 이런 식으로 기존의 컬럼들을 묶어서 사용할 수 있었음

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));
    }
}
