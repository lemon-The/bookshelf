package com.lemonthe.bookshelf.web;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.Book;
import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.data.BookRepository;
import com.lemonthe.bookshelf.data.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//TODO
//add modification mode
//add photo
//add file
//change db
//add validation
//clean up code at all
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;
    private Logger logger;

    public BookController(BookRepository bookRepo, GenreRepository genreRepo,
            AuthorRepository authorRepo) {
        this.genreRepo = genreRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @ModelAttribute(name = "all_authors")
    public List<Author> allAuthorsModel() {
        List<Author> authors = new LinkedList<>();
        authorRepo.findAll().forEach(i -> authors.add(i));
        return authors;
    }
    @ModelAttribute(name = "all_genres")
    public List<Genre> allGenresModel() {
        List<Genre> genres = new LinkedList<>();
        genreRepo.findAll().forEach(i -> genres.add(i));
        return genres;
    }
    @ModelAttribute(name = "new_book")
    public Book newBookModel() {
        return new Book();
    }

    @GetMapping
    public String bookGetMethod(
            @RequestParam(name = "author_id", required = false) Long author_id,
            @RequestParam(name = "genre_id", required = false) Long genre_id,
            Model model) {
        List<Book> books = new LinkedList<>();
        bookRepo.findAll().forEach(i -> books.add(i));
        boolean isSuitable = false;
        if (author_id != null){
            Iterator<Book> iter = books.listIterator();
            while (iter.hasNext()) {
                Book book = iter.next();
                isSuitable = false;
                for (Author author : book.getAuthors()) {
                    if (author.getId() == author_id) {
                        logger.info("Author is found: " + author.getName());
                        isSuitable = true;
                        break;
                    }
                }
                if (!isSuitable) {
                    iter.remove();
                    logger.info("Book removed: " + book.getTitle());
                }
            }
        }
        if (genre_id != null) {
            Iterator<Book> iter = books.listIterator();
            while (iter.hasNext()) {
                Book book = iter.next();
                isSuitable = false;
                for (Genre genre : book.getGenres()) {
                    if (genre.getId() == genre_id) {
                        logger.info("Genre is found: " + genre.getName());
                        isSuitable = true;
                        break;
                    }
                }
                if (!isSuitable) {
                    iter.remove();
                    logger.info("Book removed: " + book.getTitle());
                }
            }
        }
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/books")
    public String bookPostMethod(@Valid Book newBook, Errors errors) {
        if (errors.hasErrors())
            return "books";
        bookRepo.save(newBook);
        logger.info("Book: " + newBook.getTitle() + " is saved");
        return "redirect:/books";
    }
}
