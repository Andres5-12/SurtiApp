package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.Negocio;
import com.example.contabilidadMicroempresas.service.NegocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negocios")
@CrossOrigin(origins = "*")
public class NegocioController {

    private final NegocioService negocioService;

    public NegocioController(NegocioService negocioService) {
        this.negocioService = negocioService;
    }

    // 1. Listar todos los negocios (GET http://localhost:8080/api/negocios)
    @GetMapping
    public ResponseEntity<List<Negocio>> listarTodos() {
        return ResponseEntity.ok(negocioService.obtenerTodos());
    }

    // 2. Buscar por ID (GET http://localhost:8080/api/negocios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Negocio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(negocioService.obtenerPorId(id));
    }

    // 3. Registrar un nuevo negocio (POST http://localhost:8080/api/negocios)
    @PostMapping
    public ResponseEntity<Negocio> registrar(@RequestBody Negocio negocio) {
        Negocio nuevoNegocio = negocioService.guardar(negocio);
        return new ResponseEntity<>(nuevoNegocio, HttpStatus.CREATED);
    }

    // 4. Eliminar un negocio (DELETE http://localhost:8080/api/negocios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        negocioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // 5. Endpoint para actualizar parcialmente la base de la caja (PATCH http://localhost:8080/api/negocios/{id}/base)
    @PatchMapping("/{id}/base")
    public ResponseEntity<?> actualizarBase(@PathVariable Long id, @RequestBody java.util.Map<String, Double> body) {
        try {
            Double nuevaBase = body.get("baseCaja");
            if (nuevaBase == null) {
                return ResponseEntity.badRequest().body("El campo 'baseCaja' es requerido en el cuerpo de la petición.");
            }

            Negocio negocioActualizado = negocioService.actualizarBaseCaja(id, nuevaBase);
            return ResponseEntity.ok(negocioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la base de caja.");
        }
    }
}