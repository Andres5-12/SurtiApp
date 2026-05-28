package com.example.contabilidadMicroempresas.service;

import com.example.contabilidadMicroempresas.model.CierreCaja;
import java.util.List;

public interface CierreCajaService {
    CierreCaja guardar(CierreCaja cierreCaja);
    List<CierreCaja> obtenerPorNegocio(Long negocioId);
}