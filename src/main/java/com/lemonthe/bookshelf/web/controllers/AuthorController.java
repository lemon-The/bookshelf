package com.lemonthe.bookshelf.web.controllers;

import com.lemonthe.bookshelf.web.services.AuthorService;
import com.lemonthe.bookshelf.Author;

import java.util.List;

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
        logger.debug("GET /authors/ is called");
        return "authors";
    }
    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        logger.debug("GET /authors/modify/id is called with id=" + id);
        Author modAuthor = authorService.getAuthorById(id);
        model.addAttribute("mod_author", modAuthor); 
        logger.debug("Attribute \"mod_author\" is populated");
        return "modify_author";
    }
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        logger.debug("GET /authors/delete/id with id=" + id);
        authorService.deleteAuthorById(id);
        logger.info("Author with id=" + id + " is deleted");
        return "redirect:/authors";
    }

    @PostMapping
    public String authorPostMethod(@Valid @ModelAttribute("new_author") 
            Author newAuthor, Errors errors) {
        logger.debug("POST /authors/ is called");
        if (errors.hasErrors()) {
            logger.error("POST /authors/: errors are occurred");
            return "authors";
        }
        authorService.saveAuthor(newAuthor);
        logger.info("Author: " + newAuthor.getName() + " is saved");
        return "redirect:/authors";
    }
    @PostMapping("/update/{id}")
    public String modifyAuthor(@PathVariable("id") Long id,
            @Valid @ModelAttribute("mod_author") Author modifiedAuthor,
            Errors errors, Model model) {
        if (errors.hasErrors()) {
            modifiedAuthor.setId(id);
            model.addAttribute("mod_author", modifiedAuthor);
            logger.error("/authors/update/id: errors are occurred");
            return "modify_author";
        }
        modifiedAuthor.setId(id);
        authorService.saveAuthor(modifiedAuthor);
        logger.info("Author with id=" + id + " is saved");
        return "redirect:/authors";
    }
}
