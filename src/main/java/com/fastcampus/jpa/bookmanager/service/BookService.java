package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor // @Autowired대신 사용
public class BookService {
    private final BookRepository bookRepository; // @Autowired대신 사용하며 이렇게 사용하는 것이 트랜드
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    public void put() {
        this.putBookAndAuthor();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //(rollbackFor = Exception.class)
    void putBookAndAuthor()  {
        Book book = new Book();
        book.setName("JPA 트랜잭션");
        bookRepository.save(book);

        //throw new RuntimeException("@Transactional 설정하면 Unchecked 예외 시 DB에 commit 이 발생하지 않습니다.");
        // 컴파일이 예외처리하라고 지시하지 않는 UnChecked 예외는 commit이 발생하지 않는다.

        //throw new Exception("@Transactional + Checked예외 : 그래도 commit 발생");
        // Checked 예외의 경우에는 commit이 발생함

//        Author author = new Author();
//        author.setName("woosong");
//        authorRepository.save(author);
        // authorService에서 처리
        // authorService.putAuthor();
        //throw new RuntimeException("오류 발생 ! transaction은 어떻게 될까 ?"); // 예외로 인한 rollback 발생

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
            // 정상적으로 실행되겠지만, rollback 된다.
            // 둘 다 트랜잭션으로 묶여 있기 때문에 한 쪽에서 예외 난다면 둘 다 영향 끼쳐서 commit이 안 됨
        }
    }
    
        // READ_COMMITTED : commit된 데이터만 읽게 하겠음
        @Transactional(isolation = Isolation.REPEATABLE_READ) // 조회 시에 일관된 값이 츨력하게 함
        public  void get(Long id) {
            // terminal : start transaction
            // update메소드 x -> 1) terminal :update book set categeory= 'none'  // 얘는 dirty read 현상 이라고 하며, commit하면 안된다.
            // update메소드 o -> 2) terminal : insert into book ('id', 'name') values (2, 'JPA 강의2');
            System.out.println(">>> " + bookRepository.findById(id)); // break point
            System.out.println(">>> " + bookRepository.findAll());

            entityManager.clear();

            System.out.println(">>> " + bookRepository.findById(id)); // break point
            System.out.println(">>> " + bookRepository.findAll());

            bookRepository.update();  // 데이터가 안 보이는 곳에서 처리

            entityManager.clear();

            // terminal : commit

//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);

        return books;
    }
}
