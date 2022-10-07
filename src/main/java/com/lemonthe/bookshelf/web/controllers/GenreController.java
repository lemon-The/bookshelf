package com.lemonthe.bookshelf.web.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.lemonthe.bookshelf.Genre;
import com.lemonthe.bookshelf.data.GenreRepository;
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
        List<Genre> allGenres = genreService.getAllGenres();
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
        //repo.save(newGenre);
        genreService.saveGenre(newGenre);
        logger.info("Genre: " + newGenre.getName() + " is saved");
        return "redirect:/genres";
    }

    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable("id") Long id,
            Model model) {
        Genre modGenre = genreService.getAuthoById(id);
        model.addAttribute("mod_genre", modGenre); 
        logger.info("Genre ID:" + id);
        return "modify_genre";
    }
    @PostMapping("/update/{id}")
    public String modifyGenre(@PathVariable("id") Long id,
            Genre modifiedGenre) {
        modifiedGenre.setId(id);
        logger.warn("GENRE ID:" + modifiedGenre.getId());
        genreService.saveGenre(modifiedGenre);
        return "redirect:/genres";
    }
    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenreById(id);
        return "redirect:/genres";
    }
}
