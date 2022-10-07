package com.lemonthe.bookshelf.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.data.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthorService {
    private AuthorRepository authorRepo;
    private Logger logger;

    @Autowired
    public AuthorService(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
        logger = LoggerFactory.getLogger(AuthorService.class);
    }

    public Author saveAuthor(Author newAuthor) {
        return authorRepo.save(newAuthor);
    }
    public List<Author> getAllAuthors() {
        List<Author> result = new LinkedList<>();
        authorRepo.findAll().forEach(i -> result.add(i));
        return result;
    }
    public Author getAuthoById(Long id) {
        Optional<Author> author = authorRepo.findById(id);
        if (author.isEmpty())
            logger.info("getAuthorById there is not such author");
        return author.get();
    }
}
