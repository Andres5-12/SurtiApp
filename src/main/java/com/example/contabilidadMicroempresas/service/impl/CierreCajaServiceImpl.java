package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.CierreCaja;
import com.example.contabilidadMicroempresas.repository.CierreCajaRepository;
import com.example.contabilidadMicroempresas.repository.NegocioRepository;
import com.example.contabilidadMicroempresas.service.CierreCajaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CierreCajaServiceImpl implements CierreCajaService {

    private final CierreCajaRepository cierreCajaRepository;
    private final NegocioRepository negocioRepository;

    public CierreCajaServiceImpl(CierreCajaRepository cierreCajaRepository, NegocioRepository negocioRepository) {
        this.cierreCajaRepository = cierreCajaRepository;
        this.negocioRepository = negocioRepository;
    }

    @Override
    public CierreCaja guardar(CierreCaja cierreCaja) {
        // Validamos que el negocio al que se le hace el cierre exista en Supabase
        if (cierreCaja.getNegocio() == null || !negocioRepository.existsById(cierreCaja.getNegocio().getId())) {
            throw new RuntimeException("Error: El negocio asociado al cierre de caja no existe.");
        }
        return cierreCajaRepository.save(cierreCaja);
    }

    @Override
    public List<CierreCaja> obtenerPorNegocio(Long negocioId) {
        return cierreCajaRepository.findByNegocioIdOrderByFechaDesc(negocioId);
    }
}