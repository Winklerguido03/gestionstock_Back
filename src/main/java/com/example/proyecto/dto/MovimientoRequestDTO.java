package com.example.proyecto.dto;

public record MovimientoRequestDTO(
        Long idProducto,
        String tipo,      // "ENTRADA" o "SALIDA"
        Integer cantidad,
        String motivo
) {
}
