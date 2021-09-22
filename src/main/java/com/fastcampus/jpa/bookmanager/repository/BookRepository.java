package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update() ; //이 메소드를 실행하면 해당 쿼리를 실행한다.

    List<Book> findByCategoryIsNull();
    List<Book> findAllByDeletedFalse();
    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    // JPQL : JPA entity를 생성하기 위한 쿼리, entity에 존재하는 속성을 이름으로 쿼리문 작성
    @Query(value = "select b from Book b" +
            " where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")  // 파라미터를 쿼리에 넣는 방법 1. 인자와 ?이용 2. @Param 이용(자바에서는 순서 의존성을 지양함)
    List<Book> findByNameRecently(@Param("name") String name, @Param("createdAt") LocalDateTime createdAt, @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select b.name as name, b.category as category from Book b")
    List<BookNameAndCategory> findBookNameAndCategory(); // Tuple을 generic으로 줄 거면 사용 의미x

    // 메시지 오버로딩.. 이름은 같지만 파라미터 다르다면
    @Query(value = "select b.name as name, b.category as category from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);
}
