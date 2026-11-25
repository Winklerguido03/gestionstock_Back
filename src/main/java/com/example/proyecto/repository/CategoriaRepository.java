package com.example.proyecto.repository;

import com.example.proyecto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    Optional<Categoria> findById(Long aLong);
}
