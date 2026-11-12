package com.example.proyecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "Proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name="nombreProveedor",nullable = false)
    private String nombreProveedor;

    @Column(name="telefono",nullable = false)
    private int telefono;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name="direccion",nullable = false)
    private String direccion;
}
