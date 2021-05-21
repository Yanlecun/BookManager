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
//         save(...) + userRepository.flush(); // db반영 시점 조절, log상으로는 큰 변화 없다.
//        userRepository.saveAndFlush(new User("new marint", "abc@naver.com"));
//        userRepository.findAll().forEach(System.out::println);
//
//        long count = userRepository.count();
//        System.out.println(count); // 5
//
//        boolean exists = userRepository.existsById(1L); // Long type의 1번 id값, count를 통해 불러옴
//        System.out.println(exists);

        // select로 존재하는지 확인 하고 delete 실햄시킴
        // userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        //userRepository.deleteById(1L);

        // select하고, 각각의 record에 대해 delete메소드 수행
        //userRepository.deleteAll();
        // entity를 인자로 준다면 선택적으로 delete 가능
        //userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L,3L)));

        // 하지만, entity의 갯수가 많아질 경우 각각 수행하기에 상당히 비효율적인 연산이 진행된다.
        // 실행 시켜보면 인자로 준 쿼리만 실행되며, or 연산자를 사용해 entity를 삭제하는 것을 확인할 수 있다.
        // userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L,3L)));

        // delete 단 한번만 사용하는 쿼리
        //userRepository.deleteAllInBatch();

        //userRepository.findAll().forEach(System.out::println);

//        // 페이징 처리(springframwork.domain.page) = data 그룹화.. 댓글 목록 생각하면 쉽다.
//        Page<User> users = userRepository.findAll(PageRequest.of(1,3));
//        System.out.println("page : "+ users);
//        System.out.println("totalElements : "+ users.getTotalElements());
//        System.out.println("totalPages : "+ users.getTotalPages());
//        System.out.println("numberOfElemtents : "+ users.getNumberOfElements());
//        System.out.println("sort : "+ users.getSort());
//        System.out.println("size : "+ users.getSize());
//
//        users.getContent().forEach(System.out::println);


        // where절과 like절의 구현, probe로 검색하기
        // matcher로 한다면 where절만 구현하게 된다.
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // matching하는 곳에서 무시할 대상, name은 무시하지않겠다는 뜻
                .withMatcher("email",endsWith());

        Example<User> example = Example.of(new User("temp", "naver.com"),matcher) ;// probe 가짜 entity를 인자로 갖는다

        // 양방향 like 검색
        // 생각보다 많이 쓰이진 않는다.
        User user = new User();
        user.setEmail("11");
        ExampleMatcher matcher1 = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example1 = Example.of(user, matcher1);
        
        userRepository.findAll(example1).forEach(System.out::println);

    }
}
