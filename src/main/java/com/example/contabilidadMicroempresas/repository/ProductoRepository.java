package com.example.contabilidadMicroempresas.repository;

import com.example.contabilidadMicroempresas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}