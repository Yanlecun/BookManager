package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    // userHistory 값이 쌓여 있다면 복잡해지기 때문에 findById로 특정지을 필요가 있음
    List<UserHistory> findByUserId(Long userId);
}
