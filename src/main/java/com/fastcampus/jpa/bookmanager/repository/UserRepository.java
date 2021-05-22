package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // spring jpa라이브러리가 지원해주는 영역 by extends <entity, pk type>


    // User 해도 되지만, 여러 개면 오류가 나오니 List타입으로 만들자
    //List<User> findByName(String name); // 이름을 통해 유저 가지고 오는 메소드
    // Optinal<>, Set<>으로 해도 정상적으로 객체가 리턴된다.
    // query에 대한 return타입은 고정적으로 사용 안 해도 된다.
    Optional<User> findByName(String name);
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);

    // 숫자로 가져올 객체 수 정의
    List<User> findFirst1ByName(String name);
    List<User> findTop1ByName(String name);

}
