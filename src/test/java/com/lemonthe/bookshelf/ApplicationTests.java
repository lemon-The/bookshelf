package com.lemonthe.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import javax.transaction.Transactional;

import com.lemonthe.bookshelf.web.services.AuthorService;
import com.lemonthe.bookshelf.web.services.BookService;
import com.lemonthe.bookshelf.web.services.GenreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class ApplicationTests {
    private final AuthorService aServ;
    private final BookService bServ;
    private final GenreService gServ;

    Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    public ApplicationTests(AuthorService aServ, BookService bServ,
            GenreService gServ) {
        this.aServ = aServ;
        this.bServ = bServ;
        this.gServ = gServ;
    }

    @Test
    @Transactional
    public void testBookSaving() throws IOException {
        Book newBook = new Book();
        newBook.setTitle("New book");
        newBook.addGenre(gServ.getGenreById(2L));
        newBook.addAuthor(aServ.getAuthorById(1L));
        bServ.saveBook(newBook, null);
    }
    @Test
    @Transactional
    public void testAuthorSaving() {
        Author newAuthor = new Author();
        newAuthor.setName("New author");
        newAuthor.setBirthDay(LocalDate.parse("1949-03-07"));
        aServ.saveAuthor(newAuthor);
    }
    @Test
    @Transactional
    public void testGenreSaving() {
        Genre newGenre = new Genre();
        newGenre.setName("New Genre");
        newGenre.setParent(gServ.getGenreById(3L));
        gServ.saveGenre(newGenre);
    }
}
