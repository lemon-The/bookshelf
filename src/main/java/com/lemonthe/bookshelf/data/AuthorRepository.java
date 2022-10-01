package com.lemonthe.bookshelf.data;

import com.lemonthe.bookshelf.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository
        extends CrudRepository<Author, Long> {
}
