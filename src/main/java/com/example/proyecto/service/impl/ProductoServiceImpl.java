package com.example.proyecto.service.impl;

import com.example.proyecto.dto.ProductoResponseDTO;
import com.example.proyecto.model.Producto;
import com.example.proyecto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponseDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductoResponseDTO toResponse(Producto p) {
        return new ProductoResponseDTO(
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getPrecioCompra(),
                p.getPrecioVenta(),
                p.getStock(),
                p.getCategoria() != null ? p.getCategoria().getIdCategoria() : null,
                p.getCategoria() != null ? p.getCategoria().getNombreCategoria() : null,
                p.getProveedor() != null ? p.getProveedor().getIdProveedor() : null,
                p.getProveedor() != null ? p.getProveedor().getNombreProveedor() : null
        );
    }
}
