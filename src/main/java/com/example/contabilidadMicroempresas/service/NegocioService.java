package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Negocio;
import java.util.List;

public interface NegocioService {
    List<Negocio> obtenerTodos();
    Negocio guardar(Negocio negocio);
    Negocio obtenerPorId(Long id);
    Negocio actualizarBaseCaja(Long id, Double nuevaBase);
    void eliminar(Long id);
}