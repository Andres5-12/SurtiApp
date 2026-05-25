package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto guardar(Producto producto);
    Producto obtenerPorId(Long id);
    void eliminar(Long id);
}