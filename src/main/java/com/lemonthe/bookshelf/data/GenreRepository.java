package com.lemonthe.bookshelf.data;

import com.lemonthe.bookshelf.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository
        extends CrudRepository<Genre, Long> {
}
