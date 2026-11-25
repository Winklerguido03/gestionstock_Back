package com.example.proyecto.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;

    @Column(name="nombre_producto",nullable = false)
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

    public Long getIdProducto() {
        return idProducto;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }


    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setStock(int stock) {
        this.stock=stock;
    }
}
