package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> obtenerTodas();
    Categoria guardar(Categoria categoria);
    Categoria obtenerPorId(Long id);
    void eliminar(Long id);
}