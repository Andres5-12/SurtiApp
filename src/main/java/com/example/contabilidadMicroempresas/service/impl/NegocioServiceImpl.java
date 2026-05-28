package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.Negocio;
import com.example.contabilidadMicroempresas.repository.NegocioRepository;
import com.example.contabilidadMicroempresas.service.NegocioService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NegocioServiceImpl implements NegocioService {

    private final NegocioRepository negocioRepository;

    public NegocioServiceImpl(NegocioRepository negocioRepository) {
        this.negocioRepository = negocioRepository;
    }

    @Override
    public List<Negocio> obtenerTodos() {
        return negocioRepository.findAll();
    }

    @Override
    public Negocio guardar(Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    @Override
    public Negocio obtenerPorId(Long id) {
        return negocioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con el ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        // Validación o borrado físico/lógico según tus requerimientos
        if (!negocioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Negocio no encontrado con el ID: " + id);
        }
        negocioRepository.deleteById(id);
    }
    @Override
    public Negocio actualizarBaseCaja(Long id, Double nuevaBase) {
        // Buscamos el negocio existente; si no existe, lanzamos una excepción
        Negocio negocioExistente = negocioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: El negocio con ID " + id + " no existe."));

        // Validamos que el valor no sea negativo por lógica de negocio
        if (nuevaBase == null || nuevaBase < 0) {
            throw new IllegalArgumentException("La base de caja no puede ser un valor negativo o nulo.");
        }

        // Actualizamos únicamente el campo solicitado
        negocioExistente.setBaseCaja(nuevaBase);

        return negocioRepository.save(negocioExistente);
    }
}