package com.example.contabilidadMicroempresas.controller;

import com.example.contabilidadMicroempresas.model.Usuario;
import com.example.contabilidadMicroempresas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Listar todos (GET http://localhost:8080/api/usuarios)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // 2. Buscar por ID (GET http://localhost:8080/api/usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // 3. Crear Usuario (POST http://localhost:8080/api/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // 4. Eliminar Usuario (DELETE http://localhost:8080/api/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // 5. Iniciar Sesión (POST http://localhost:8080/api/usuarios/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        // Buscamos al usuario por su email
        Usuario usuarioExistente = usuarioService.obtenerPorEmail(loginRequest.getEmail());

        if (usuarioExistente != null && usuarioExistente.getPassword().equals(loginRequest.getPassword())) {
            // Si las credenciales coinciden, devolvemos el usuario completo (incluye su ID)
            return ResponseEntity.ok(usuarioExistente);
        } else {
            // Si falla, devolvemos un estado 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}