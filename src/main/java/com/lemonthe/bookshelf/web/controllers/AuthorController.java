package com.lemonthe.bookshelf.web.controllers;

import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.web.services.AuthorService;
import com.lemonthe.bookshelf.Author;

import java.util.List;
import java.util.LinkedList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;
    private Logger logger;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
        logger = LoggerFactory.getLogger(AuthorController.class);
    }

    @ModelAttribute(name = "all_authors")
    public List<Author> allAouthorsModel() {
        return authorService.getAllAuthors();
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
        authorService.saveAuthor(newAuthor);
        logger.info("Author: " + newAuthor.getName() + " is saved");
        return "redirect:/authors";
    }

    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        Author modAuthor = authorService.getAuthoById(id);
        model.addAttribute("mod_author", modAuthor); 
        logger.info("Author ID:" + id);
        return "modify_author";
    }
    @PostMapping("/update/{id}")
    public String modifyAuthor(@PathVariable("id") Long id,
            Author modifiedAuthor) {
        modifiedAuthor.setId(id);
        logger.warn("AUTHOR ID:" + modifiedAuthor.getId());
        authorService.saveAuthor(modifiedAuthor);
        return "redirect:/authors";
    }
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/authors";
    }
}
