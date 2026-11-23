package com.example.proyecto.dto;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long idProducto,
        String nombreProducto,
        BigDecimal precioCompra,
        BigDecimal precioVenta,
        int stock,
        Long idCategoria,
        String nombreCategoria,
        Long idProveedor,
        String nombreProveedor
) {
}
