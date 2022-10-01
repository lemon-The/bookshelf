package com.lemonthe.bookshelf.web;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateByStringConverter
    implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String str) {
        if (str.isBlank())
            return null;
        return LocalDate.parse(str);
    }
}
