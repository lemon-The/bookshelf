package com.lemonthe.bookshelf.web.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.web.services.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private GenreService genreService;
    private Logger logger;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
        logger = LoggerFactory.getLogger(GenreService.class);
    }

    @ModelAttribute(name = "all_genres")
    public List<Genre> allGenresModel() {
        return genreService.getAllGenres();
    }
    @ModelAttribute(name = "new_genre")
    public Genre newGenreModel() {
        return new Genre();
    }

    @GetMapping
    public String genreGetMethod(
            @RequestParam(name = "genre_id", required = false) Long genre_id,
            Model model) {
        logger.debug("GET /genres/ is called with id="
                + Objects.toString(genre_id, "0"));
        List<Genre> allGenres = genreService.getAllGenres();
        if (genre_id != null) {
            List<Genre> reduced = new LinkedList<>();
            for (Genre genre : allGenres) {
                if (genre.getId() == genre_id) {
                    addAllSubgenresToList(genre, reduced);
                }
            }
            model.addAttribute("genres_list", reduced);
        } else {
            model.addAttribute("genres_list", allGenres);
        }
        logger.debug("Attribute \"genres_list\" is populated");
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
    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        logger.debug("GET /genes/delete/id is called with id=" + id);
        genreService.deleteGenreById(id);
        logger.info("Genre with id=" + id + " is deleted");
        return "redirect:/genres";
    }
    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        logger.debug("GET /genres/modify/id is called with id=" + id);
        Genre modGenre = genreService.getGenreById(id);
        model.addAttribute("mod_genre", modGenre); 
        logger.debug("Attribute \"mod_genre\" is populated");
        return "modify_genre";
    }

    @PostMapping
    public String genrePostMethod(@Valid @ModelAttribute("new_genre")
            Genre newGenre, Errors errors) {
        logger.debug("POST /genres/ is called");
        if (errors.hasErrors()) {
            logger.error("/genres/: errors are occurred");
            return "genres";
        }
        genreService.saveGenre(newGenre);
        logger.info("Genre: " + newGenre.getName() + " is saved");
        return "redirect:/genres";
    }
    @PostMapping("/update/{id}")
    public String modifyGenre(@PathVariable("id") Long id,
            @Valid @ModelAttribute("mod_genre") Genre modifiedGenre, 
            Errors errors, Model model) {
        logger.debug("POST /genres/update/id is called with id=" + id);
        if (errors.hasErrors()) {
            modifiedGenre.setId(id);
            model.addAttribute("mod_genre", modifiedGenre);
            logger.error("/genres/update/id: errors are occurred");
            return "genres";
        }
        modifiedGenre.setId(id);
        genreService.saveGenre(modifiedGenre);
        logger.info("Genre with id=" + id + " is saved");
        return "redirect:/genres";
    }
}
