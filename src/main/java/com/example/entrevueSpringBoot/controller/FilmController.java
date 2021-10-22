package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.persistence.model.Film;
import com.example.entrevueSpringBoot.persistence.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/api/films/{filmId}")
    public ResponseEntity<Film> getFilm(@PathVariable(name = "filmId") final long id){
        return filmService.getFilmById(id);
    }

    @PostMapping("/api/films")
    public ResponseEntity<Film> updateFilm(@RequestBody final Film film){
        return filmService.saveFilm(film);
    }
}
