package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @GeneratedValue
    private Long id;

    private Long bookId;

    private float averageReviewScore; // primitive vs wrappered type
                                      // 기본값이 0이냐 null이냐

    private int reviewCount;

}
