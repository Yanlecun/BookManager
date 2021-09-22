package com.fastcampus.jpa.bookmanager.repository.dto;

import lombok.* ;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookNameAndCategory {
    private String name;
    private String category;
//    String getName();
//    String getCategory();
}
