package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.*;

import lombok.* ;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long bookId;
    @OneToOne(optional = false)
    private Book book;

    private float averageReviewScore;
    private int reviewCount;

}
