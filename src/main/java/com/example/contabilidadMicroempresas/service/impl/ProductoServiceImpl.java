package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.Producto;
import com.example.contabilidadMicroempresas.repository.ProductoRepository;
import com.example.contabilidadMicroempresas.service.ProductoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}