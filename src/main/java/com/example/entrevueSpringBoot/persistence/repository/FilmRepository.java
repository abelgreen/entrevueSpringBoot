package com.example.entrevueSpringBoot.persistence.repository;

import com.example.entrevueSpringBoot.persistence.model.Film;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long>, JpaSpecificationExecutor<Film> {
}
