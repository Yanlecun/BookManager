package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // @Autowired대신 사용
public class BookService {
    private final BookRepository bookRepository; // @Autowired대신 사용하며 이렇게 사용하는 것이 트랜드
    private final AuthorRepository authorRepository;

    public void put() {
        this.putBookAndAuthor();
    }

    @Transactional //(rollbackFor = Exception.class)7
    private void putBookAndAuthor()  {
        Book book = new Book();
        book.setName("JPA 트랜잭션");
        bookRepository.save(book);

        Author author = new Author();
        author.setName("woosong");
        authorRepository.save(author);

        throw new RuntimeException("@Transactional 설정하면 Unchecked 예외 시 DB에 commit 이 발생하지 않습니다.");
        // 컴파일이 예외처리하라고 지시하지 않는 UnChecked 예외는 commit이 발생하지 않는다.

        //throw new Exception("@Transactional + Checked예외 : 그래도 commit 발생");
        // Checked 예외의 경우에는 commit이 발생함
    }
}
