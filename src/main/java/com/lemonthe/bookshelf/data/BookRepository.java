package com.lemonthe.bookshelf.data;

import com.lemonthe.bookshelf.Book;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository
        extends CrudRepository<Book, Long> {
}
