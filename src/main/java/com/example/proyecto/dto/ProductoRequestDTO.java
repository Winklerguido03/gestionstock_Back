package com.example.proyecto.dto;

import java.math.BigDecimal;

public record ProductoRequestDTO(
        String nombreProducto,
        BigDecimal precioCompra,
        BigDecimal precioVenta,
        Integer stock,
        Long idCategoria,
        Long idProveedor
) {
}
