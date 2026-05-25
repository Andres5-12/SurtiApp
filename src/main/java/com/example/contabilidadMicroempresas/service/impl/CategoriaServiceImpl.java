package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.Categoria;
import com.example.contabilidadMicroempresas.repository.CategoriaRepository;
import com.example.contabilidadMicroempresas.service.CategoriaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Categoría no encontrada con el ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}