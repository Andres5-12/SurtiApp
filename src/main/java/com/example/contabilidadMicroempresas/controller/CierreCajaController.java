package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.CierreCaja;
import com.example.contabilidadMicroempresas.service.CierreCajaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cierres")
@CrossOrigin(origins = "*")
public class CierreCajaController {

    private final CierreCajaService cierreCajaService;

    public CierreCajaController(CierreCajaService cierreCajaService) {
        this.cierreCajaService = cierreCajaService;
    }

    // 1. Guardar registro del cierre (POST http://localhost:8080/api/cierres)
    @PostMapping
    public ResponseEntity<CierreCaja> registrar(@RequestBody CierreCaja cierreCaja) {
        CierreCaja nuevoCierre = cierreCajaService.guardar(cierreCaja);
        return new ResponseEntity<>(nuevoCierre, HttpStatus.CREATED);
    }

    // 2. Obtener histórico por Negocio (GET http://localhost:8080/api/cierres/negocio/{negocioId})
    @GetMapping("/negocio/{negocioId}")
    public ResponseEntity<List<CierreCaja>> listarPorNegocio(@PathVariable Long negocioId) {
        return ResponseEntity.ok(cierreCajaService.obtenerPorNegocio(negocioId));
    }
}