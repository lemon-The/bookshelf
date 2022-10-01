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
        List<Genre> allGenres = new LinkedList<>();
        repo.findAll().forEach(i -> allGenres.add(i));
        if (genre_id != null) {
            List<Genre> reduced = new LinkedList<>();
            for (Genre genre : allGenres) {
                if (genre.getId() == genre_id) {
                    addAllSubgenresToList(genre, reduced);
                }
            }
            logger.info("Genres: " + allGenres);
            model.addAttribute("genres_list", reduced);
        } else {
            model.addAttribute("genres_list", allGenres);
        }
        return "genres";
    }

    private void addAllSubgenresToList(Genre current, List<Genre> list) {
        if (current == null)
            return;
        list.add(current);
        if (current.getSubgenres() == null)
            return;
        for (Genre sub : current.getSubgenres())
            addAllSubgenresToList(sub, list);
    }

    @PostMapping
    public String genrePostMethod(@Valid Genre newGenre, Errors errors) {
        if (errors.hasErrors())
            return "genres";
        repo.save(newGenre);
        logger.info("Genre: " + newGenre.getName() + " is saved");
        return "redirect:/genres";
    }
}
