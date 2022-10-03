package com.lemonthe.bookshelf.web;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.data.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorByIdConverter implements Converter<Long, Author>{
    private AuthorRepository repo;
    @Autowired
    public AuthorByIdConverter(AuthorRepository repo) {
        this.repo = repo;
    }
    @Override
    public Author convert(Long id) {
        return repo.findById(id).orElse(null);
    }
}
