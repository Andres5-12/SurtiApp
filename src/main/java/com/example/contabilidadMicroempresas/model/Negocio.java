package com.example.contabilidadMicroempresas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "negocios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Negocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo_negocio", length = 50)
    private String tipoNegocio;

    @Column(name = "identificacion_fiscal", length = 50)
    private String identificacionFiscal; // NIT o RUT en Colombia

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}