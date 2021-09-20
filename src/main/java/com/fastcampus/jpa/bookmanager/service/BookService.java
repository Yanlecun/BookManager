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

    @Transactional // 추가
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 트랜잭션");
        bookRepository.save(book);

        Author author = new Author();
        author.setName("woosong");
        authorRepository.save(author);
    }
}
