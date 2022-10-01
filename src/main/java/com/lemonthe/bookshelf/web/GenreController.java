package com.lemonthe.bookshelf.web;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.data.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private GenreRepository repo;
    private Logger logger;

    @Autowired
    public GenreController(GenreRepository repo) {
        this.repo = repo;
        logger = LoggerFactory.getLogger(GenreController.class);
    }

    @ModelAttribute(name = "all_genres")
    public List<Genre> allGenresModel() {
        List<Genre> genres = new LinkedList<>();
        repo.findAll().forEach(i -> genres.add(i));
        return genres;
    }

    @ModelAttribute(name = "new_genre")
    public Genre newGenreModel() {
        return new Genre();
    }

    @GetMapping
    public String genreGetMethod(
            @RequestParam(name = "genre_id", required = false) Long genre_id,
            Model model) {
        List<Genre> genres = new LinkedList<>();
        repo.findAll().forEach(i -> genres.add(i));
        if (genre_id != null) {
            List<Genre> res = new LinkedList<>();
            for (Genre genre : genres) {
                if (genre.getId() == genre_id) {
                    recurs(res, genre);
                }
            }
            logger.info("Genres: " + genres);
            model.addAttribute("genres_list", res);
        } else {
            model.addAttribute("genres_list", genres);
        }
        return "genres";
    }

    private void recurs(List<Genre> genres, Genre node) {
        if (node == null)
            return;
        genres.add(node);
        if (node.getSubgenres() == null)
            return;
        for (Genre chld : node.getSubgenres())
            recurs(genres, chld);
    }

    //@GetMapping
    //public String getGenrePage() {
    //    logger.info(getClass().getName() + "GET request");
    //    return "genres";
    //}

    @PostMapping
    public String genrePostMethod(@Valid Genre newGenre, Errors errors) {
        if (errors.hasErrors())
            return "genres";
        repo.save(newGenre);
        logger.info("Genre: " + newGenre.getName() + " is saved");
        return "redirect:/genres";
    }
}
