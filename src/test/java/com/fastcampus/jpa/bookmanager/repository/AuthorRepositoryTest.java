package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository ;
    @Autowired
    BookRepository bookRepository;

    @Test
    public void manyToManyTest() {
        Book book1 = givenBook("이론책1");
        Book book2 = givenBook("이론책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("woosong");
        Author author2 = givenAuthor("thddn");

        book1.addAuthor(author1);//book1.setAuthors(Lists.newArrayList(author1));
        book2.addAuthor(author2);//book2.setAuthors(Lists.newArrayList(author2));
        book3.addAuthor(author1,author2); //book3.setAuthors(Lists.newArrayList(author1, author2));
        book4.addAuthor(author1,author2); //book4.setAuthors(Lists.newArrayList(author1,author2));

        author1.addBook(book1, book2, book3); // author1.setBooks(Lists.newArrayList(book1, book2, book3));
        author2.addBook(book2, book3, book4); // author2.setBooks(Lists.newArrayList(book2, book3, book4));

        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        authorRepository.saveAll(Lists.newArrayList(author1,author2));

        System.out.println("author through book : " + bookRepository.findAll().get(2).getAuthors());
        System.out.println("book through author : " + authorRepository.findAll().get(0).getBooks());
    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

}