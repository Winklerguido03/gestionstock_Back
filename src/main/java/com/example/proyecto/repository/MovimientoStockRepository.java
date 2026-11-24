package com.example.proyecto.repository;

import com.example.proyecto.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoStockRepository extends JpaRepository<Movimiento,Long> {

    List<Movimiento> findAllByOrderByFechaDesc();

}
