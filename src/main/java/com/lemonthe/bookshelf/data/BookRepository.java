package com.lemonthe.bookshelf.data;

import java.util.List;

import com.lemonthe.bookshelf.Author;
import com.lemonthe.bookshelf.Book;
import com.lemonthe.bookshelf.Genre;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository
        extends CrudRepository<Book, Long> {
    List<Book> findByAuthors(Author author);
    List<Book> findByGenres(Genre genreId);
}
