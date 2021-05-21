package com.fastcampus.jpa.bookmanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder  // 객체 생성하고 필드값을 주입함
@Entity //  orm이 말하는 객체 선언, 구분할 수 있는 고유 값이 있어야 한다.
public class User {
    @Id // User라는 table의 pk값
    @GeneratedValue // 순차적으로 값 증가시키기
    private Long id;

    @NonNull // RequiredArgsConstructor 어노테이션이 챙길 속성이다
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
