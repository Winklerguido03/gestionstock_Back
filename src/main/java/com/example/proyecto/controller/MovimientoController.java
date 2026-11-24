package com.example.proyecto.controller;

import com.example.proyecto.dto.MovimientoRequestDTO;
import com.example.proyecto.model.Movimiento;
import com.example.proyecto.service.impl.MovimientoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {

    private final MovimientoServiceImpl movimientoService;

    public MovimientoController(MovimientoServiceImpl movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody MovimientoRequestDTO dto) {
        try {
            movimientoService.registrarMovimiento(dto);
            return ResponseEntity.ok(Map.of("message", "Movimiento registrado correctamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

}
