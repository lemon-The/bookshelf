package com.lemonthe.bookshelf.web.services;

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
        logger.debug("Saving author");
        return authorRepo.save(newAuthor);
    }
    public List<Author> getAllAuthors() {
        List<Author> result = new LinkedList<>();
        authorRepo.findAll().forEach(i -> result.add(i));
        if (result.isEmpty())
            logger.warn("There are no authors");
        return result;
    }
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepo.findById(id);
        if (author.isEmpty())
            logger.info("Author with id=" + id + " does not exist");
        return author.get();
    }
    public void deleteAuthorById(Long id) {
        logger.debug("Deleting author with id=" + id);
        authorRepo.deleteById(id);
        logger.debug("Author with id=" + id + " is deleted");
    }
}
