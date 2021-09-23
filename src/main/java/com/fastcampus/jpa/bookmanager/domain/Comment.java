package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.*;

import lombok.* ;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate // 영향을 받은 부분만 update 실행
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @ToString.Exclude
    private Review review; //하나의 리뷰에 많은 댓글

    @Column(columnDefinition = "datetime(6) default now(6)")
    private LocalDateTime commentedAt;
}
