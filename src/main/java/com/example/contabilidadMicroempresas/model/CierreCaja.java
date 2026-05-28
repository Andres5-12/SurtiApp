package com.example.contabilidadMicroempresas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cierres_caja")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio; // Relación directa con el negocio

    @Column(nullable = false)
    private LocalDate fecha; // Se maneja como LocalDate para búsquedas precisas de balances diarios

    @Column(name = "ingresos_efectivo", nullable = false)
    private Double ingresosEfectivo = 0.0;

    @Column(name = "ingresos_transferencia", nullable = false)
    private Double ingresosTransferencia = 0.0;

    @Column(nullable = false)
    private Double egresos = 0.0;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial = 0.0;

    @Column(name = "saldo_final_esperado", nullable = false)
    private Double saldoFinalEsperado = 0.0;

    @Column(name = "saldo_final_real", nullable = false)
    private Double saldoFinalReal = 0.0;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}