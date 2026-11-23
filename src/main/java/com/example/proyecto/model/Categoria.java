package com.example.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Long idCategoria;

    @Column(name="nombre_categoria",nullable = false)
    private String nombreCategoria;

    public Long getIdCategoria() {
        return idCategoria;
    }


    public String getNombreCategoria() {
        return nombreCategoria;
    }
}
