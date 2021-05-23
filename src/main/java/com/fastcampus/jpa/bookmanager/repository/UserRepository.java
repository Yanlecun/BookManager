package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    // where + and
    List<User> findByEmailAndName(String email, String name);
    // where + or
    List<User> findByEmailOrName(String email, String name);

    // 1일 전 이후에 만들어진 레코드들 출력 (Before도 가능 )
    List<User> findByCreatedAtAfter(LocalDateTime time);

    // 어떤 값보다 초과인 경우 출력 (where 숫자값 > n)
    List<User> findByIdAfter(Long id);

    // where Created_at > ?, before이랑 after보다 범용적으로 사용 가능
    List<User> findByCreatedAtGreaterThan(LocalDateTime time);
    // where Created_at >= ?
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime time);

    // where created_at ? and ?
    List<User> findByCreatedAtBetween(LocalDateTime time1, LocalDateTime time2);
    List<User> findByIdBetween(Long id1, Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(long id1, Long id2);

    // where id is not null
    List<User> findByIdIsNotNull();

    // IsEmpty / IsNotEmpty can only be used on collection properties!
    // collection 타입의 NotEmpty를 확인한다.
    // 많이 사용되지 않는 메소드, 그냥 `Email != null && Email != ''` 이 아닌 것만 이해해도 된다.
    //List<User> findByEmailIsNotEmpty();  -> 에러
    //List<User> findByAddressIsNotEmpty();

    // where name in/or ( ? , ? )
    List<User> findByNameIn(List<String> names);

    // like 검색 with %,, 실제로 parameter로 받을 경우에는 문자열 결합을 하는데, 이는 가독성을 해친다.
    // 따라서 코드 가독성을 높이기 위해 다음과 같은 세 개의 메소드를 사용한다.
    List<User> findByNameLike(String name);

    // like 검색 (prefix, suffix, affix)
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);  // = Contaning


    // 코드 가독성을 높이기 위해 Is나 Equals를 추가하기도 한다.
    List<User> findUserByNameIs(String name);
    List<User> findUserByName(String name);
    List<User> findUserByNameEquals(String name);

    // order-by
    List<User> findUserTop2ByNameOrderByIdDesc(String name); // Asc = 오름차순

    // order by ? desc하고 , ? asc 의 결괏값의 제일 첫번째
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    // JpaRepository 잘 살펴보면 Sort를 인자로 받음
    // name인 조건 + sort에 추가로 column을 인자로 줘서 출력하기
    // 메소드 이름이 끝없이 길어지면 가독성 너무 안 좋기 떄문에 Sort를 인자로 주는 것도 좋다
    // 다만, 정답은 없기에 어떤 것이 좋은지 계속 고민해보자
    List<User> findTopByName(String name, Sort sort);


    // Page(전체 페이지에 대한 정보들, page에 대한 응답값) 인터페이스 extends slice(데이터 묶음의 일부)
    // Pagable page에 대한 요청값
    Page<User> findByName(String name, Pageable pageable);

    // Native Query, User타입해도 되지만, 더 raw한 값으로 살펴보자
    @Query(value="select * from user limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
