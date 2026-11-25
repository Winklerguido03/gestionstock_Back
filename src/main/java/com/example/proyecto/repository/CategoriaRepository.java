package com.example.proyecto.repository;

import com.example.proyecto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
