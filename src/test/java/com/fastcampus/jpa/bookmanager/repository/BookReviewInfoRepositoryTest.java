package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {
        givenBookReviewInfo();
    }

    @Test
    void bookTest() {
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBook();
                //bookRepository.findById(bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBookId()
                //).orElseThrow(RuntimeException::new);

        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository.findById(1L).orElseThrow(RuntimeException::new).getBookReviewInfo();
        System.out.println(">>> " + result2);
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 교과서");
        book.setAuthorId(1L);
        //book.setPublisherId(1L);
        return bookRepository.save(book);
        //System.out.println(">>> " + bookRepository.findAll());
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }
}