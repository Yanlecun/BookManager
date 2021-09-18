package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)  // 상속받은 클래스 필드까지 실제 컬럼으로 동작시키겠다고 알림
@EqualsAndHashCode(callSuper = true) // BaseEntity의 속성들을 내것으로 만들기
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder  // 객체 생성하고 필드값을 주입함
@Entity //  ORM이 말하는 객체 선언, 구분할 수 있는 고유 값이 있어야 한다.
@EntityListeners(value = { UserEntityListener.class}) // AuditingEntityListener.class 이것도 삭제 BaseEntity에서 처리
@Table(name="user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User extends BaseEntity {
    @Id // User라는 table의 pk값
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull // RequiredArgsConstructor 어노테이션이 챙길 속성이다
    private String name;
    @NonNull
    private String email;

//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
    // BaseEntity 에서 사용하니까 삭제

    @Transient
    private String testData;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    //@OneToMany(fetch = FetchType.EAGER)
    //private List<Address> address;


//    @PrePersist // persist(insert)가 호출되기 전에 호출되는 메소드
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//    @PreUpdate  // merge메소드 전
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
    //MyEntityListener에서 대신 역할을 해줄거기 때문에 주석처리
}
