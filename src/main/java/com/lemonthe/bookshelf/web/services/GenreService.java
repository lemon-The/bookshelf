package com.lemonthe.bookshelf.web.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.data.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GenreService {
    private GenreRepository genreRepo;
    private Logger logger;

    @Autowired
    public GenreService(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
        logger = LoggerFactory.getLogger(GenreService.class);
    }

    public Genre saveGenre(Genre newGenre) {
        logger.debug("Saving genre");
        return genreRepo.save(newGenre);
    }
    public List<Genre> getAllGenres() {
        List<Genre> result = new LinkedList<>();
        genreRepo.findAll().forEach(i -> result.add(i));
        if (result.isEmpty())
            logger.warn("There are no genres");
        return result;
    }
    public Genre getGenreById(Long id) {
        Optional<Genre> genre = genreRepo.findById(id);
        if (genre.isEmpty())
            logger.info("Genre with id=" + id + " does not exist");
        return genre.get();
    }
    public void deleteGenreById(Long id) {
        logger.debug("Deleting genre with id=" + id);
        genreRepo.deleteById(id);
        logger.debug("Genre with id=" + id + " is deleted");
    }
}
