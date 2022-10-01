package com.lemonthe.bookshelf.web;

import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.Author;

import java.util.List;
import java.util.LinkedList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private AuthorRepository repo;
    private Logger logger;

    @Autowired
    public AuthorController(AuthorRepository repo) {
        this.repo = repo;
        logger = LoggerFactory.getLogger(AuthorController.class);
    }

    @ModelAttribute(name = "all_authors")
    public List<Author> allAouthorsModel() {
        List<Author> authors = new LinkedList<>();
        repo.findAll().forEach(i -> authors.add(i));
        return authors;
    }
    @ModelAttribute(name = "new_author")
    public Author newAuthorModel() {
        return new Author();
    }

    @GetMapping
    public String getAuthorPage() {
        logger.info(getClass().getName() + " GET request");
        return "authors";
    }

    @PostMapping
    public String authorPostMethod(@Valid Author newAuthor, Errors errors) {
        logger.info(getClass().getName() + " POST request");
        if (errors.hasErrors())
            return "authors";
        repo.save(newAuthor);
        logger.info("Author: " + newAuthor.getName() + " is saved");
        return "redirect:/authors";
    }
}
