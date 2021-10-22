package com.example.entrevueSpringBoot.persistence.service;

import com.example.entrevueSpringBoot.persistence.model.Film;
import com.example.entrevueSpringBoot.persistence.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final FilmRepository repository;
    @Autowired
    public FilmService(final FilmRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Film> getFilmById(long id) {
        return repository.findById(id)
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    public ResponseEntity<Film> saveFilm(Film film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(film));
    }
}
