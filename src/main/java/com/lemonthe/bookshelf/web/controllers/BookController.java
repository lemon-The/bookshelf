package com.lemonthe.bookshelf.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.Book;
import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.Photo;
import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.data.BookRepository;
import com.lemonthe.bookshelf.data.GenreRepository;
import com.lemonthe.bookshelf.data.PhotoRepository;
import com.lemonthe.bookshelf.web.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//TODO
//find out about Spring Service
//Correct photo names and directories
//
//
//
//add modification mode
//add photo
//add file
//change db
//add validation
//clean up code at all
@Controller
@RequestMapping("/books")
public class BookController {
    private GenreRepository genreRepo;
    private BookRepository bookRepo;
    private AuthorRepository authorRepo;
    private BookService bookService;
    private Logger logger;

    @Autowired
    public BookController(BookRepository bookRepo, GenreRepository genreRepo,
            AuthorRepository authorRepo, BookService bookService) {
        this.genreRepo = genreRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.bookService = bookService;
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

    @GetMapping("/cover/{id}")
    @ResponseBody
    public void showCover(@PathVariable("id") Long id,
            HttpServletResponse response)
            throws  ServletException, IOException {
        logger.info("/cover/id is called with id=" + id);
        Photo photo = bookService.getBookById(id).getPhoto();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(photo.getData());
        response.getOutputStream().close();
        logger.info("cover/id is finished");
    }
    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        Book modBook = bookService.getBookById(id);
        model.addAttribute("mod_book", modBook); 
        logger.info("Book ID:" + id);
        return "modify_book";
    }
    @PostMapping("/update/{id}")
    public String modifyBook(@PathVariable("id") Long id,
            Book modifiedBook,  
            @RequestParam(name = "new_photo", required = false) MultipartFile photo) throws IOException {
        modifiedBook.setId(id);
        logger.warn("BOOOK ID:" + modifiedBook.getId());
        bookService.saveBook(modifiedBook, photo);
        return "redirect:/books";
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

    @PostMapping("/upload")
    public String bookPostMethod(
            @RequestParam(name = "new_photo", required = false) MultipartFile photo,
            @Valid Book newBook, Errors errors) throws IOException {
        logger.info("POOOOOST");
        if (errors.hasErrors()) {
            logger.info(errors.getErrorCount() + "");
            logger.info(errors.getNestedPath());
            logger.info(errors.getObjectName());
            logger.info(errors.toString());
            return "books";
        }
        logger.info("Book ID:" + newBook.getId());
        bookService.saveBook(newBook, photo);
        logger.info("Book: " + newBook.getTitle() + " is saved");
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
