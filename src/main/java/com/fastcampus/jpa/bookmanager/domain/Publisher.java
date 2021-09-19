package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.*;

import lombok.* ;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Publisher extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id") // publisher_books 같은 이상한 중간 테이블 생성되는 거 막기
    private List<Book> books = new ArrayList<>();

}

