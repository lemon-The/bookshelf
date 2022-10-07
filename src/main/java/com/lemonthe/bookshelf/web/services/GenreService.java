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
        return genreRepo.save(newGenre);
    }
    public List<Genre> getAllGenres() {
        List<Genre> result = new LinkedList<>();
        genreRepo.findAll().forEach(i -> result.add(i));
        return result;
    }
    public Genre getAuthoById(Long id) {
        Optional<Genre> genre = genreRepo.findById(id);
        if (genre.isEmpty())
            logger.info("getGenreById there is not such genre");
        return genre.get();
    }
    public void deleteGenreById(Long id) {
        genreRepo.deleteById(id);
    }
}
