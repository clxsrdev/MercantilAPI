package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsucursal")
    private Long idSucursal;

    @Column(name = "nombre_suc", nullable = false, unique = true)
    private String nombreSucursal;

    @Column(name = "estado_suc", nullable = false)
    private String estadoSucursal;

    @PrePersist
    private void prePersist() {
        if (estadoSucursal == null) {
            estadoSucursal = "Activa";
        }
    }
}
