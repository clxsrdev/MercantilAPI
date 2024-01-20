package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "montacargas")
public class Montacargas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monta")
    private Long idMontacargas;

    @ManyToOne
    @JoinColumn(name = "id_mm", nullable = false)
    private marcaMonta marcaMonta;

    @ManyToOne
    @JoinColumn(name = "idsucursal", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "id_motor", nullable = false)
    private Motores motor;

    @Column(name = "nombre_monta", nullable = false)
    private String nombre;

    @Column(name = "modelo_monta", nullable = false)
    private String modelo;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "no_economico", nullable = false, unique = true)
    private String noEconomico;

    @Column(name = "serie", nullable = false, unique = true)
    private String serie;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "carga_maxima", nullable = false)
    private int cargaMaxima;

    @PrePersist
    private void prePersist() {
        if (estado == null) {
            estado = "Activa";
        }
    }
}
