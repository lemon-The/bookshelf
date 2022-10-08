package com.lemonthe.bookshelf.web.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.Book;
import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.Photo;
import com.lemonthe.bookshelf.web.services.AuthorService;
import com.lemonthe.bookshelf.web.services.BookService;
import com.lemonthe.bookshelf.web.services.GenreService;

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

@Controller
@RequestMapping("/books")
public class BookController {
    private GenreService genreService;
    private AuthorService authorService;
    private BookService bookService;
    private Logger logger;

    @Autowired
    public BookController(GenreService genreService,
            AuthorService authorService, BookService bookService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @ModelAttribute(name = "all_authors")
    public List<Author> allAuthorsModel() {
        return authorService.getAllAuthors();
    }
    @ModelAttribute(name = "all_genres")
    public List<Genre> allGenresModel() {
        return genreService.getAllGenres();
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
        logger.debug("GET /books/cover/id is called with id=" + id);
        Photo photo = bookService.getBookById(id).getPhoto();
        response.setContentType("image/jpeg, image/jpg, image/png, "
                + "image/gif");
        response.getOutputStream().write(photo.getData());
        response.getOutputStream().close();
        logger.info("/books/cover/id is finished");
    }
    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        logger.debug("GET /books/modify/id is called with id=" + id);
        Book modBook = bookService.getBookById(id);
        model.addAttribute("mod_book", modBook); 
        logger.debug("Attribute \"mod_book\" is populated");
        return "modify_book";
    }
    @GetMapping
    public String bookGetMethod(
            @RequestParam(name = "author_id", required = false)
            Long author_id,
            @RequestParam(name = "genre_id", required = false)
            Long genre_id,
            Model model) {
        logger.debug("GET /books/ is called with author_id="
                + Objects.toString(author_id, "0")+ ", genre_id="
                + Objects.toString(genre_id, "0"));
        List<Book> books = new LinkedList<>();
        if (author_id != null)
            books.addAll(bookService.getBooksByAuthorId(author_id));
        if (genre_id != null)
            books.addAll(bookService.getBooksByGenreId(genre_id));
        if (books.isEmpty())
            books.addAll(bookService.getAllBooks());
        model.addAttribute("books", books);
        logger.debug("Attribute \"books\" is populated");
        return "books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        logger.debug("GET /books/delete/id is called with id=" + id);
        bookService.deleteBookById(id);
        logger.info("Book with id=" + id + " is deleted");
        return "redirect:/books";
    }

    @PostMapping("/update/{id}")
    public String modifyBook(@PathVariable("id") Long id,
            @RequestParam(name = "new_photo", required = false)
            MultipartFile photo,
            @Valid @ModelAttribute("mod_book") Book modifiedBook,  
            Errors errors, Model model) {
        logger.debug("POST /books/update/id is called with id=" + id);
        if (errors.hasErrors()) {
            modifiedBook.setId(id);
            model.addAttribute("mod_book", modifiedBook);
            logger.error("/books/update/id: errors are occurred");
            return "modify_book";
        }
        modifiedBook.setId(id);
        try {
            bookService.saveBook(modifiedBook, photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Book with id=" + id + " is saved");
        return "redirect:/books";
    }
    @PostMapping("/upload")
    public String bookPostMethod(
            @RequestParam(name = "new_photo", required = false)
            MultipartFile photo,
            @Valid @ModelAttribute("new_book") Book newBook,
            Errors errors) throws IOException {
        logger.debug("POST /books/upload/id is called");
        if (errors.hasErrors()) {
            logger.error("/books/upload/id: errors are occurred");
            return "books";
        }
        bookService.saveBook(newBook, photo);
        logger.info("Book: " + newBook.getTitle() + " is saved");
        return "redirect:/books";
    }
}
