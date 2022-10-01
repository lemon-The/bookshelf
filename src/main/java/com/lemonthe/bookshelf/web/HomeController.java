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
@RequestMapping("/")
public class HomeController {
    @Autowired
    GenreRepository repo;
    @Autowired
    BookRepository bookRepo;
    @Autowired
    AuthorRepository authorRepository;
    Logger logger;

    public HomeController(BookRepository bookRepo, GenreRepository repo,
            AuthorRepository authorRepository) {
        this.repo = repo;
        this.bookRepo = bookRepo;
        this.authorRepository = authorRepository;
        this.logger = LoggerFactory.getLogger(HomeController.class);
    }

    @ModelAttribute(name = "authors")
    public List<Author> authorsModel() {
        List<Author> authors = new LinkedList<>();
        authorRepository.findAll().forEach(i -> authors.add(i));
        return authors;
    }

    //@ModelAttribute(name = "genre")
    //public Genre genreMode() {
    //    return new Genre();
    //}

    //@ModelAttribute(name = "author")
    //public Author authorModel() {
    //    return new Author();
    //}

    @ModelAttribute(name = "book")
    public Book bookModel() {
        return new Book();
    }

    @ModelAttribute(name = "all_genres")
    public List<Genre> allGenres() {
        List<Genre> genres = new LinkedList<>();
        repo.findAll().forEach(i -> genres.add(i));
        return genres;
    }

    //@GetMapping("/genres")
    //public String genreGetMethod(
    //        @RequestParam(name = "genre_id", required = false) Long genre_id,
    //        Model model) {
    //    List<Genre> genres = new LinkedList<>();
    //    repo.findAll().forEach(i -> genres.add(i));
    //    boolean isSuitable = false;
    //    if (genre_id != null) {
    //        List<Genre> res = new LinkedList<>();
    //        for (Genre genre : genres) {
    //            if (genre.getId() == genre_id) {
    //                recurs(res, genre);
    //            }
    //        }
    //        logger.info("Genres: " + genres);
    //        model.addAttribute("genres", res);
    //    } else {
    //        model.addAttribute("genres", genres);
    //    }
    //    return "genres";
    //}

    //private void recurs(List<Genre> genres, Genre node) {
    //    if (node == null)
    //        return;
    //    genres.add(node);
    //    if (node.getSubgenres() == null)
    //        return;
    //    for (Genre chld : node.getSubgenres())
    //        recurs(genres, chld);
    //}

    //@GetMapping("/authors")
    //public String authorGetMethod() {
    //    return "authors";
    //}

    @GetMapping("/books")
    public String booksGetMethod(
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

    @GetMapping
    public String rootGetMethod() {
        return "redirect:/books";
    }

    @PostMapping("/books")
    public String bookPostMethod(@Valid Book newBook, Errors errors) {
        if (errors.hasErrors())
            return "books";
        bookRepo.save(newBook);
        logger.info("Book: " + newBook.getTitle() + " is saved");
        return "redirect:/books";
    }
    //@PostMapping("/genres")
    //public String genrePostMethod(@Valid Genre newGenre, Errors errors) {
    //    if (errors.hasErrors())
    //        return "genres";
    //    repo.save(newGenre);
    //    logger.info("Genre: " + newGenre.getName() + " is saved");
    //    return "redirect:/genres";
    //}
    //@PostMapping("/authors")
    //public String authorPostMethod(@Valid Author newAuthor, Errors errors) {
    //    if (errors.hasErrors())
    //        return "authors";
    //    authorRepository.save(newAuthor);
    //    logger.info("Author: " + newAuthor.getName() + " is saved");
    //    return "redirect:/authors";
    //}
}
