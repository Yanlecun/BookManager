package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service // 서비스 빈 표시
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();  // 비영속 상태
        user.setName("newUser");
        user.setEmail("new@naver.com");

        userRepository.save(user); // save 구현 코드에서 entity manager를 사용해 저장하고 있으므로 저장된다.
        entityManager.persist(user); // 직접 저장하기

        user.setName("newUserAfterPersist"); // 이렇게만 해도 저장된다.
        // 변경내역을 DB에 적용할 시점에 현재 Entity와 비교해서 변경된 부분 적용해 알아서 DB에 적용한다.

        entityManager.detach(user); // = clear, close

        entityManager.merge(user); // 변경한 부분 적용된다.

        entityManager.clear(); //save 이후 변경 작업에 대해서 clear 시켜서 적용되지 않게 함 + flush메소드로 저장하고 사용한다.

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1); // 이후 merge 할 경우 실행오류
    }



}
