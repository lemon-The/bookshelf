package com.lemonthe.bookshelf.data;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PathByStringConverter
    implements AttributeConverter<Path, String>{
    @Override
    public String convertToDatabaseColumn(Path path) {
        return path.toString();
    }
    @Override
    public Path convertToEntityAttribute(String string) {
        return Paths.get(string);
    }
}
