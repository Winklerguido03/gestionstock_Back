package com.example.proyecto.service.impl;

import com.example.proyecto.dto.MovimientoRequestDTO;
import com.example.proyecto.enums.Tipo;
import com.example.proyecto.model.DetalleMovimiento;
import com.example.proyecto.model.Movimiento;
import com.example.proyecto.model.Producto;
import com.example.proyecto.model.Usuario;
import com.example.proyecto.repository.MovimientoStockRepository;
import com.example.proyecto.repository.ProductoRepository;
import com.example.proyecto.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MovimientoServiceImpl {

    private final MovimientoStockRepository movimientoRepository;
    private final DetalleMovimientoRepository detalleMovimientoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimientoServiceImpl(MovimientoStockRepository movimientoRepository,
                                 DetalleMovimientoRepository detalleMovimientoRepository,
                                 ProductoRepository productoRepository,
                                 UsuarioRepository usuarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.detalleMovimientoRepository = detalleMovimientoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void registrarMovimiento(MovimientoRequestDTO dto) {
        if (dto.cantidad() == null || dto.cantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Producto producto = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        String identifier = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(identifier)
                .or(() -> usuarioRepository.findByUsername(identifier))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String tipo = dto.tipo().toUpperCase();

        if ("ENTRADA".equals(tipo)) {
            producto.setStock(producto.getStock() + dto.cantidad());
        } else if ("SALIDA".equals(tipo)) {
            if (producto.getStock() < dto.cantidad()) {
                throw new IllegalArgumentException("Stock insuficiente. Disponible: " + producto.getStock());
            }
            producto.setStock(producto.getStock() - dto.cantidad());
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido (use ENTRADA o SALIDA)");
        }

        // 1. Actualizar el stock del producto
        productoRepository.save(producto);

        // 2. Guardar la cabecera del movimiento (fecha, tipo, usuario)
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(Tipo.valueOf(tipo));
        movimiento.setUsuario(usuario);
        movimiento.setFecha(LocalDate.now());
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);

        // 3. Guardar el detalle del movimiento (movimiento, producto, cantidad)
        DetalleMovimiento detalle = new DetalleMovimiento();
        detalle.setMovimiento(movimientoGuardado);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.cantidad());
        detalleMovimientoRepository.save(detalle);
    }

}
