package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.Transaccion;
import com.example.contabilidadMicroempresas.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*") // Permite que tu app móvil o web se conecte sin problemas de CORS
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // 1. Obtener todas las transacciones (GET http://localhost:8080/api/transacciones)
    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTodas() {
        return ResponseEntity.ok(transaccionService.obtenerTodas());
    }

    // 2. Obtener por ID (GET http://localhost:8080/api/transacciones/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.obtenerPorId(id));
    }

    // 3. Filtrar por Negocio y Estado (GET http://localhost:8080/api/transacciones/negocio/{negocioId}?estado=ACTIVO)
    @GetMapping("/negocio/{negocioId}")
    public ResponseEntity<List<Transaccion>> listarPorNegocio(
            @PathVariable Long negocioId,
            @RequestParam(defaultValue = "ACTIVO") String estado) {
        return ResponseEntity.ok(transaccionService.obtenerPorNegocioYEstado(negocioId, estado));
    }

    // 4. Crear una nueva transacción (POST http://localhost:8080/api/transacciones)
    @PostMapping
    public ResponseEntity<Transaccion> registrar(@RequestBody Transaccion transaccion) {
        Transaccion nuevaTransaccion = transaccionService.guardar(transaccion);
        return new ResponseEntity<>(nuevaTransaccion, HttpStatus.CREATED);
    }

    // 5. Eliminar de forma lógica (DELETE http://localhost:8080/api/transacciones/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        transaccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de Actualización (PUT http://localhost:8080/api/transacciones/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizar(@PathVariable Long id, @RequestBody Transaccion transaccion) {
        Transaccion transaccionActualizada = transaccionService.actualizar(id, transaccion);
        return ResponseEntity.ok(transaccionActualizada);
    }
}