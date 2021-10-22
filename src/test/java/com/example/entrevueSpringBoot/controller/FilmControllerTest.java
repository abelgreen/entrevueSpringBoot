package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.persistence.model.Acteur;
import com.example.entrevueSpringBoot.persistence.model.Film;
import com.example.entrevueSpringBoot.persistence.service.FilmService;
import com.example.entrevueSpringBoot.tag.UnitTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@UnitTest
@AutoConfigureMockMvc
public class FilmControllerTest {
    @Autowired
    private MockMvc mvc;
    private static Film film;
    private static String jsonContent;
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws Exception {
        film = Film.builder().description("desc 1").titre("titre 1")
                .acteurs(List.of(Acteur.builder().nom("jack").prenom("dorsi").build(),
                        Acteur.builder().nom("john").prenom("lenon").build())).build();
        jsonContent = mapper.writeValueAsString(film);
    }

    @Test
    void getFilmHasFilm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/films").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent));

        mvc.perform(MockMvcRequestBuilders.get("/api/films/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(film.getDescription()))
                .andExpect(jsonPath("$.titre").value(film.getTitre()))
                .andExpect(jsonPath("$.acteurs[?(@.nom=='jack' && @.prenom=='dorsi')]").exists())
                .andExpect(jsonPath("$.acteurs[?(@.nom=='john' && @.prenom=='lenon')]").exists());
    }

    @Test
    void getFilmNoFilm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/films/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    void updateFilm() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/films").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value(film.getDescription()))
                .andExpect(jsonPath("$.titre").value(film.getTitre()))
                .andExpect(jsonPath("$.acteurs[?(@.nom=='jack' && @.prenom=='dorsi')]").exists())
                .andExpect(jsonPath("$.acteurs[?(@.nom=='john' && @.prenom=='lenon')]").exists());

    }
}