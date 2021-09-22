package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try {
            bookService.put(); //putBookAndAuthor();
        } catch (Exception e) {
            System.out.println(">>>" + e.getMessage());
        }
        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA 트랜잭션 독립성");

        bookRepository.save(book);
        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }

    @Test
    void converterErrorTest() {
        bookService.getAll();
        bookRepository.findAll().forEach(System.out::println); // 실행해보면, status값이 null이 되어버림
        // Transactional이 완료되는 시점에 변경된 내용이 있는 경우 그 데이터를 db에 영속화 하는 과정에서 update함
        // converter가 덜 구현이 된 경우 해당 값이 변경된 것 처럼 감지해버려서 null로 읽어버린다.
        // 조회만 했는데.. 실제 데이터가 유실되는 경우가 생겨버림
    }
}