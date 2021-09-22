package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;

import javax.persistence.*;

import lombok.* ;

@Embeddable // 임베드 가능하게 하기
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;
    private String district;
    @Column(name = "address_detail")
    private String detail;
    private String zipCode;
}
