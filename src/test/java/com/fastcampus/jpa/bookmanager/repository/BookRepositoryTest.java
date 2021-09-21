package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.Publisher;
import com.fastcampus.jpa.bookmanager.domain.Review;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new  Book();
        book.setName("스프링으로 배우는");
//        book.setAuthor("송우");
//        book.setAuthorId(1L);
        //book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("woosong11@naver.com"); // 보통은 인증정보를 통해 가져오지만.. 그냥 편의를 위해 find사용
        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview(){
        givenReview(givenUser(), givenBook(givenPublisher()));
    }
    private User givenUser() {
        return userRepository.findByEmail("woosong11@naver.com");
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 교과서");
//        book.setAuthorId(1L);
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("교보");
        return publisherRepository.save(publisher);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("책 좋아요");
        review.setContent("추천합니다");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);
        reviewRepository.save(review);
    }


    @Test
    @Transactional
    void bookCascadeTest() {
        Book book = new Book() ;
        book.setName("JPA Casacde");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        //publisher.addBook(book); // publisher.getBooks().add(book); 이렇게 사용할 수 있지만, setter를 사용하는 것이 클린한 코드임

        bookRepository.save(book);

        System.out.println("books : "+ bookRepository.findAll());
        System.out.println("publisher : " + publisherRepository.findAll());


        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("변경할게");   // update에 대한 것도 영속성 전이를 정의해줘야함
        bookRepository.save(book1);
        System.out.println("publisher : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
        //bookRepository.delete(book2);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        // data.sql 값들 지우기 + 위에 delete문 주석처리
        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null); // 연관관계는 제거 되었지만 (null값) publisher값 자체는 db에 남아있음
        bookRepository.save(book3);
        System.out.println("publisher of book3 :" + bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    @Transactional
    void bookRemoveCascadeTest() {
        bookRepository.deleteById(1L); // REMOVE에 의해 연결되어 있던 publusher도 같이 삭제된다.

        // data.sql는 단순 쿼리만 실행하는 것이므로 createAt과 updateAt 리스너는 적용되지 않는다.
        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void softDelete() {
        System.out.println(bookRepository.findAll()); // deleted라고 flag된 것이 조회 된다.

//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
//        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println); // 매번 이렇게 where조건 이용해서 하면 실수했을때 치명적이게 된다.
    }
}
