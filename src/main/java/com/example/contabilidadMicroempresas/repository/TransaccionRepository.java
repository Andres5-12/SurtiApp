package com.example.contabilidadMicroempresas.repository;

import com.example.contabilidadMicroempresas.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByNegocioIdOrderByFechaDesc(Long negocioId);

    // Para calcular rangos de fechas (Balances mensuales, semanales, etc.)
    List<Transaccion> findByNegocioIdAndFechaBetween(Long negocioId, LocalDateTime inicio, LocalDateTime fin);

    // Para Cuentas por Cobrar/Pagar (Fiados pendientes)
    List<Transaccion> findByNegocioIdAndEstado(Long negocioId, String estado);
    // Busca todas las transacciones asociadas a una categoría específica
    List<Transaccion> findByCategoria_Id(Long categoriaId);
}