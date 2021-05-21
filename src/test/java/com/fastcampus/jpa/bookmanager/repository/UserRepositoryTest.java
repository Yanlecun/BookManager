package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import lombok.Builder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring context를 로딩해서 테스트에 활용하겠다는 뜻
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void crud() {
        //userRepository.save(new User()); // 생성 후 저장

        //flush; jpa context에서 가지고 있는 db값을 db에 반영하도록 지시하는 메소드
        //userRepository.findAll().forEach(System.out::println);
        // forEach(User user : userRepository.findAll())

        //List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));

        ////List<Long> ids = new ArrayList<>(); 해서 하나하나 add 할 필요 없이 한 번에 찾아서 찾기 가능
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));
        users.forEach(System.out::println);

//        // hibernate_sequence 가 있어서 id값이 계속 증가된다.
//        User user1 = new User("jack", "jck@naver.com");
//        User user2 = new User("coke", "collar@naver.com");
//        userRepository.saveAll(Lists.newArrayList(user1,user2));
//
//        List<User> users1 = userRepository.findAll();
//        users1.forEach(System.out::println);

        // no Session 에러, -> Transaction 어노테이션 추가해주면 session을 유지시켜준다.
        // lazy fetch / em = entity manager에 의한 관리
//        User user = userRepository.getOne(1L);
//        System.out.println(user);

        // 원래는 Opetional<User> user 해주어야한다. orElse를 통해 없는 경우 반환값을 null로 지정함
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);

    }
}
