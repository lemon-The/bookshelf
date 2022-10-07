package com.lemonthe.bookshelf.web;

import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.data.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreByIdConverter implements Converter<Long, Genre>{
    @Autowired
    private GenreRepository repo;
    public GenreByIdConverter(GenreRepository repo) {
        this.repo = repo;
    }
    @Override
    public Genre convert(Long id) {
        return repo.findById(id).orElse(null);
    }
}
