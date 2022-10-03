package com.lemonthe.bookshelf.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.lemonthe.bookshelf.Book;
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
    private Path photoDirectory;
    private Logger logger;

    @Autowired
    public BookService(BookRepository bookRepo,
            PhotoRepository photoRepo) {
        this.bookRepo = bookRepo;
        this.photoRepo = photoRepo;
        this.photoDirectory = Paths.get("./target/classes/static/images/books_covers");
        logger = LoggerFactory.getLogger(BookService.class);
    }

    public Book saveBook(Book newBook, MultipartFile photo)
            throws IOException {
        Photo savedPhoto = savePhoto(photo);
        newBook.setPhoto(savedPhoto);
        return bookRepo.save(newBook);
    }
    private Photo savePhoto(MultipartFile photo)
        throws IOException {
        if (!Files.exists(photoDirectory)) {
            Files.createDirectory(photoDirectory);
        }
        Path fileName = Paths.get(photo.getOriginalFilename());
        Path fullFileName = photoDirectory.resolve(fileName);
        Files.write(fullFileName, photo.getBytes());
        Photo newPhoto = new Photo();
        newPhoto.setPath(fullFileName);
        logger.info("Saving " + fullFileName.toString());
        return photoRepo.save(newPhoto);
    }

}
