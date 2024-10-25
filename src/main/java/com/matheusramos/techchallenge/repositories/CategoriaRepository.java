package com.matheusramos.techchallenge.repositories;

import com.matheusramos.techchallenge.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}