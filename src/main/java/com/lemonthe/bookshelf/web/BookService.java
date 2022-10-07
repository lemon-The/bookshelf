package com.lemonthe.bookshelf.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
        Photo savedPhoto = savePhoto(newBook, photo);
        logger.info("Photo " + savedPhoto.getId() + " is saved");
        newBook.setPhoto(savedPhoto);
        return bookRepo.save(newBook);
    }
    private Photo savePhoto(Book newBook, MultipartFile photo) throws IOException {
        Photo result = new Photo();
        if (photo == null || photo.isEmpty()) {
            logger.info("photo is not present");
            Optional<Book> tmp = bookRepo.findById(newBook.getId());
            if (tmp.isPresent()) {
                logger.info("Photo already exist");
                return tmp.get().getPhoto();
            } else {
                logger.info("Photo does not exist. Setting default");
                Path filePath =
                    Paths.get("./src/main/resources/static/images/default.jpg");
                byte[] def = Files.readAllBytes(filePath);
                result.setData(def);
            }
        } else {
            result.setData(photo.getBytes());
        }
        return photoRepo.save(result);
    }
    //private Photo savePhoto(MultipartFile photo)
    //    throws IOException {
    //    if (!Files.exists(photoDirectory)) {
    //        Files.createDirectory(photoDirectory);
    //    }
    //    Path fileName = Paths.get(photo.getOriginalFilename());
    //    Path fullFileName = photoDirectory.resolve(fileName);
    //    Files.write(fullFileName, photo.getBytes());
    //    Photo newPhoto = new Photo();
    //    newPhoto.setPath(fullFileName);
    //    logger.info("Saving " + fullFileName.toString());
    //    return photoRepo.save(newPhoto);
    //}

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isEmpty())
            logger.info("getBookById there is not such book");
        return book.get();
    }

}
