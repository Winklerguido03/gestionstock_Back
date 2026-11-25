package com.example.proyecto.service.impl;

import com.example.proyecto.dto.ProductoRequestDTO;
import com.example.proyecto.dto.ProductoResponseDTO;
import com.example.proyecto.model.Categoria;
import com.example.proyecto.model.Producto;
import com.example.proyecto.model.Proveedor;
import com.example.proyecto.repository.CategoriaRepository;
import com.example.proyecto.repository.ProductoRepository;
import com.example.proyecto.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProveedorRepository proveedorRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        this.categoriaRepository = categoriaRepository;
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

    @Transactional
    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        if (dto.nombreProducto() == null || dto.nombreProducto().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (dto.precioCompra() == null || dto.precioCompra().doubleValue() < 0 ||
                dto.precioVenta() == null || dto.precioVenta().doubleValue() < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativos");
        }
        if (dto.stock() == null || dto.stock() < 0) {
            throw new IllegalArgumentException("El stock inicial no puede ser negativo");
        }

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        Proveedor proveedor = proveedorRepository.findById(dto.idProveedor())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        Producto producto = new Producto();
        producto.setNombreProducto(dto.nombreProducto().trim());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
        producto.setStock(dto.stock());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);

        Producto guardado = productoRepository.save(producto);

        // Devuelve el mismo DTO plano que usas para listar
        return new ProductoResponseDTO(
                guardado.getIdProducto(),
                guardado.getNombreProducto(),
                guardado.getPrecioCompra(),
                guardado.getPrecioVenta(),
                guardado.getStock(),
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                proveedor.getIdProveedor(),
                proveedor.getNombreProveedor()
        );
    }
}
