package com.lemonthe.bookshelf.web;

import java.nio.file.Path;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StringByPathConverter
    implements Converter<Path, String> {
    private Logger logger =
        LoggerFactory.getLogger(StringByPathConverter.class);
    @Override
    public String convert(Path path) {
        logger.info("Converting " + path.toString());
        return path.toString();
    }
}
