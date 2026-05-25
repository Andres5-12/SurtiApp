package com.example.contabilidadMicroempresas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. REEMPLAZADO: Ahora es una relación real con la tabla Negocio
    @ManyToOne
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    // 2. NUEVO: Agregamos la relación obligatoria con la tabla Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 150)
    private String descripcion;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false, length = 20)
    private String tipo; // "INGRESO" o "EGRESO"

    @Column(nullable = false, length = 30)
    private String estado; // "ACTIVO", "ANULADO", "PENDIENTE"

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        if (this.estado == null) {
            this.estado = "ACTIVO";
        }
    }
}