package com.example.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleMovimiento")
public class DetalleMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleMovimiento;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    // Relación con Movimiento
    @ManyToOne
    @JoinColumn(name = "movimiento_idMovimiento", nullable = false)
    private Movimiento movimiento;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_idProducto", nullable = false)
    private Producto producto;


    public void setMovimiento(Movimiento movimientoGuardado) {
        this.movimiento=movimientoGuardado;
    }

    public void setProducto(Producto producto) {
        this.producto=producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad=cantidad;
    }
}
