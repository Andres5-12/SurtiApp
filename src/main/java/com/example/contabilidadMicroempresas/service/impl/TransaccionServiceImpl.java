package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.Transaccion;
import com.example.contabilidadMicroempresas.repository.CategoriaRepository;
import com.example.contabilidadMicroempresas.repository.NegocioRepository;
import com.example.contabilidadMicroempresas.repository.TransaccionRepository;
import com.example.contabilidadMicroempresas.service.TransaccionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final NegocioRepository negocioRepository;
    private final CategoriaRepository categoriaRepository;

    // Inyección por constructor (Práctica recomendada en lugar de @Autowired)
    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, NegocioRepository negocioRepository, CategoriaRepository categoriaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.negocioRepository = negocioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Transaccion> obtenerTodas() {
        return transaccionRepository.findAll();
    }

    @Override
    public List<Transaccion> obtenerPorNegocioYEstado(Long negocioId, String estado) {
        return transaccionRepository.findByNegocioIdAndEstado(negocioId, estado);
    }

    @Override
    public Transaccion guardar(Transaccion transaccion) {
        // Validar que el negocio asignado exista en Supabase
        if (transaccion.getNegocio() == null || !negocioRepository.existsById(transaccion.getNegocio().getId())) {
            throw new RuntimeException("Error: El negocio asociado no existe.");
        }
        // Validar que la categoría asignada exista en Supabase
        if (transaccion.getCategoria() == null || !categoriaRepository.existsById(transaccion.getCategoria().getId())) {
            throw new RuntimeException("Error: La categoría asociada no existe.");
        }
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion obtenerPorId(Long id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con el ID: " + id));
    }

    @Override
    public Transaccion actualizar(Long id, Transaccion transaccionDetalles) {
        Transaccion transaccionExistente = obtenerPorId(id);

        // Validar que el negocio y la categoría existan antes de actualizar (tal como en el método guardar)
        if (transaccionDetalles.getNegocio() == null || !negocioRepository.existsById(transaccionDetalles.getNegocio().getId())) {
            throw new RuntimeException("Error: El negocio asociado no existe.");
        }
        if (transaccionDetalles.getCategoria() == null || !categoriaRepository.existsById(transaccionDetalles.getCategoria().getId())) {
            throw new RuntimeException("Error: La categoría asociada no existe.");
        }

        // Actualizamos los campos permitidos
        transaccionExistente.setDescripcion(transaccionDetalles.getDescripcion());
        transaccionExistente.setMonto(transaccionDetalles.getMonto());
        transaccionExistente.setTipo(transaccionDetalles.getTipo());
        transaccionExistente.setEstado(transaccionDetalles.getEstado());
        transaccionExistente.setFecha(transaccionDetalles.getFecha());
        transaccionExistente.setNegocio(transaccionDetalles.getNegocio());
        transaccionExistente.setCategoria(transaccionDetalles.getCategoria());
        transaccionExistente.setMetodoPago(transaccionDetalles.getMetodoPago()); // Nuevo campo

        return transaccionRepository.save(transaccionExistente);
    }

    @Override
    public void eliminar(Long id) {
        Transaccion transaccion = obtenerPorId(id);
        // En lugar de un borrado físico, puedes cambiar el estado a "ELIMINADO" o "INACTIVO"
        transaccion.setEstado("ELIMINADO");
        transaccionRepository.save(transaccion);
    }

}