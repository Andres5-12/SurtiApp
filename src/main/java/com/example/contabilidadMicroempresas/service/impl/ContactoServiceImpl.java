package com.example.contabilidadMicroempresas.service.impl;

import com.example.contabilidadMicroempresas.model.Contacto;
import com.example.contabilidadMicroempresas.repository.ContactoRepository;
import com.example.contabilidadMicroempresas.service.ContactoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoServiceImpl(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public Contacto obtenerPorId(Long id) {
        return contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con el ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!contactoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Contacto no encontrado con el ID: " + id);
        }
        contactoRepository.deleteById(id);
    }
}