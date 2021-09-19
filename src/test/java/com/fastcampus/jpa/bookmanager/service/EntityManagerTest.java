package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

    @SpringBootTest
    @Transactional
    public class EntityManagerTest {
        @Autowired
        private EntityManager entityManager;
        @Test
        void entityManagerTest() {
            // UserRepository.findAll() 과 같은 동작하고 있음
            System.out.println(entityManager.createQuery("select u from User u").getResultList());
        }

    @Autowired
    private UserRepository userRepository;
    @Test
    void cacheFindTest() {
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2(){
        User user = userRepository.findById(1L).get();
        user.setName("thddn");

        userRepository.save(user);
        //userRepository.flush(); // db반영

        System.out.println("------------------");

        user.setEmail("thddn@naver.com");
        userRepository.save(user);
        //userRepository.flush();

//        System.out.println(">>> 1 : " + userRepository.findById(1L).get());
//
//        userRepository.flush();
//
//        System.out.println(">>> 2 : " + userRepository.findById(1L).get());

        System.out.println(userRepository.findAll()); // select * from user
    }
}
