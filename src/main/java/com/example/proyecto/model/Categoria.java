package com.example.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name="nombreCategoria",nullable = false)
    private String nombreCategoria;
}
