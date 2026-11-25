package com.example.proyecto.model;

import com.example.proyecto.enums.Tipo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_idUsuario", nullable = false)
    private Usuario usuario;

    public void setTipo(Tipo tipo) {
        this.tipo=tipo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
    }

    public void setFecha (LocalDate fecha){
        this.fecha=fecha;
    }
}
