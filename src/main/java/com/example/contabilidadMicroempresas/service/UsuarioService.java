package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    Usuario obtenerPorId(Long id);
    void eliminar(Long id);
}