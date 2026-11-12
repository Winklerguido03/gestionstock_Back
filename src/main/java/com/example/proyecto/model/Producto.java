package com.example.proyecto.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name="nombreProducto",nullable = false)
    private String nombreProducto;

    @Column(name="precio_compra",nullable = false)
    private BigDecimal precioCompra;

    @Column(name="precio_venta",nullable = false)
    private BigDecimal precioVenta;

    @Column(name="stock",nullable = false)
    private int stock;

    // Relación con Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_idCategoria", nullable = false)
    private Categoria categoria;

    // Relación con Proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_idProveedor", nullable = false)
    private Proveedor proveedor;

}
