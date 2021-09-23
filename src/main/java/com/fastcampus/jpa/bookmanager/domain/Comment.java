package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.*;

import lombok.* ;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @ToString.Exclude
    private Review review; //하나의 리뷰에 많은 댓글

    @Column(columnDefinition = "datetime")
    private LocalDateTime commentedAt;
}
