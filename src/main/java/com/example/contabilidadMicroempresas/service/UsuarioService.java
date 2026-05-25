package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    Usuario obtenerPorId(Long id);
    Usuario obtenerPorEmail(String email);
    void eliminar(Long id);

}