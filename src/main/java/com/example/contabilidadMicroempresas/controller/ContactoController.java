package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.Contacto;
import com.example.contabilidadMicroempresas.service.ContactoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "*")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public ResponseEntity<List<Contacto>> listarTodos() {
        return ResponseEntity.ok(contactoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contactoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Contacto> registrar(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoService.guardar(contacto);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contactoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}