package com.lemonthe.bookshelf.web.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.Book;
import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.Photo;
import com.lemonthe.bookshelf.data.BookRepository;
import com.lemonthe.bookshelf.data.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookService {
    private BookRepository bookRepo;
    private PhotoRepository photoRepo;
    private AuthorService authorService;
    private GenreService genreService;
    private Logger logger;

    @Autowired
    public BookService(BookRepository bookRepo, PhotoRepository photoRepo,
            AuthorService authorService, GenreService genreService) {
        this.bookRepo = bookRepo;
        this.photoRepo = photoRepo;
        this.authorService = authorService;
        this.genreService = genreService;
        logger = LoggerFactory.getLogger(BookService.class);
    }

    public Book saveBook(Book newBook, MultipartFile photo)
            throws IOException {
        logger.debug("Saving book");
        Photo savedPhoto = savePhoto(newBook, photo);
        logger.debug("Photo with id=" + savedPhoto.getId() + " is saved");
        newBook.setPhoto(savedPhoto);
        return bookRepo.save(newBook);
    }
    private Photo savePhoto(Book newBook, MultipartFile photo) throws IOException {
        logger.debug("Saving photo");
        Photo result = new Photo();
        if (photo == null || photo.isEmpty()) {
            logger.debug("New photo is not present");
            Long id = newBook.getId();
            if (id != null) {
                Optional<Book> tmp = bookRepo.findById(id);
                logger.info("Photo with id=" + id + " already exists");
                return tmp.get().getPhoto();
            } else {
                logger.info("Photo does not exist. Setting default photo");
                Path filePath =
                    Paths.get("./src/main/resources/static/images/default.jpg");
                byte[] def = Files.readAllBytes(filePath);
                result.setData(def);
            }
        } else {
            logger.debug("New photo is present. Saving");
            result.setData(photo.getBytes());
        }
        return photoRepo.save(result);
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isEmpty())
            logger.warn("Book with id=" + id + " does not exist");
        return book.get();
    }
    public List<Book> getBooksByAuthorId(Long id) {
        Author athr = authorService.getAuthorById(id);
        List<Book> result = new LinkedList<>();
        if (athr == null) {
            logger.warn("Author with id=" + id + " does not exist");
        } else {
            bookRepo.findByAuthors(athr).forEach(i -> result.add(i));
        }
        return result;
    }
    public List<Book> getBooksByGenreId(Long id) {
        Genre gnr = genreService.getGenreById(id);
        List<Book> result = new LinkedList<>();
        if (gnr == null) {
            logger.warn("Gerne with id=" + id + " does not exist");
        } else {
            bookRepo.findByGenres(gnr).forEach(i -> result.add(i));
        }
        return result;
    }
    public List<Book> getAllBooks() {
        List<Book> result = new LinkedList<>();
        bookRepo.findAll().forEach(i -> result.add(i));
        return result;
    }
    public void deleteBookById(Long id) {
        logger.debug("Deleting book with id=" + id);
        bookRepo.deleteById(id);
        logger.debug("Book with id=" + id + " is deleted");
    }
}
