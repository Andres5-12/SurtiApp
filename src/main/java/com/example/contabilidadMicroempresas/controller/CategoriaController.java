package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.Categoria;
import com.example.contabilidadMicroempresas.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // 1. Listar todas (GET http://localhost:8080/api/categorias)
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    // 2. Buscar por ID (GET http://localhost:8080/api/categorias/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    // 3. Crear Categoría (POST http://localhost:8080/api/categorias)
    @PostMapping
    public ResponseEntity<Categoria> registrar(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.guardar(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // 4. Eliminar Categoría (DELETE http://localhost:8080/api/categorias/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}