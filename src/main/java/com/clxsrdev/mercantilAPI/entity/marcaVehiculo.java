package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marcavehiculo")
public class marcaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mv")
    private Long idMarcaVehiculo;

    @Column(name = "nombre_mv", nullable = false, unique = true)
    private String nombreMarcaVehiculo;

    @Column(name = "estado_mv", nullable = false)
    private String estadoMarcaVehiculo;

    @PrePersist
    private void prePersist() {
        if (estadoMarcaVehiculo == null) {
            estadoMarcaVehiculo = "Activa";
        }
    }
}