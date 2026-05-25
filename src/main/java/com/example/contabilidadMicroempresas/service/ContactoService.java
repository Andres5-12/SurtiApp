package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Contacto;
import java.util.List;

public interface ContactoService {
    List<Contacto> obtenerTodos();
    Contacto guardar(Contacto contacto);
    Contacto obtenerPorId(Long id);
    void eliminar(Long id);
}