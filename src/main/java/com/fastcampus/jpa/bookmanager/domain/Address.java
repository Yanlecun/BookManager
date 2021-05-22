package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Address {
    @Id
    private Long id;
}
