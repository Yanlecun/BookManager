package com.fastcampus.jpa.bookmanager.domain;

import lombok.* ;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private float score;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER가 기본인 릴레이션 어노테이션이 있음.. 학습용으로 쿼리 간단하게 하기 위해 잠시 LAZY
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Book book;

    @OneToMany(fetch = FetchType.LAZY) // LAZY가 기본이지만 N+1 이슈를 확인하기 위해 EAGER화
    @JoinColumn(name="review_id")
    private List<Comment> comments;

}
