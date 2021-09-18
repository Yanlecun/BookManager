package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    private String name;
    private String email;
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    //private String testData;

    //private Gender gender;
}
