package com.fastcampus.jpa.bookmanager.domain.converter;

import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // JPA에서 사용하는 converter클래스라고 표시 // autoApply 처리하고 Convert를 삭제하면 BookStatus 오류마크 뜨지만 문제x
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        // BookStatus를 객체를 받아서 db에 저장할 때 어떻게 저장할 것이냐
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        // NullPointerException 조심해야함 .. Converter의 경우 DB에 근접해있기 때문에 에러나면 안된다.
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
