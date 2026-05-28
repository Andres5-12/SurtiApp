package com.example.contabilidadMicroempresas.repository;

import com.example.contabilidadMicroempresas.model.CierreCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CierreCajaRepository extends JpaRepository<CierreCaja, Long> {
    // Busca los cierres de un negocio ordenados por fecha (Más reciente primero)
    List<CierreCaja> findByNegocioIdOrderByFechaDesc(Long negocioId);
}