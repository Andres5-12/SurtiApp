package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.Transaccion;
import java.util.List;

public interface TransaccionService {
    List<Transaccion> obtenerTodas();
    List<Transaccion> obtenerPorNegocioYEstado(Long negocioId, String estado);
    Transaccion guardar(Transaccion transaccion);
    Transaccion obtenerPorId(Long id);
    Transaccion actualizar(Long id, Transaccion transaccionDetalles);
    void eliminar(Long id);
}