package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehi")
    private Long idVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_mv", nullable = false, referencedColumnName = "id_mv")
    private marcaVehiculo marcaVehiculo;

    @ManyToOne
    @JoinColumn(name = "idsucursal", nullable = false, referencedColumnName = "idsucursal")
    private Sucursal sucursal;

    @Column(name = "nombre_vehi", nullable = false, unique = true)
    private String nombre;

    @Column(name = "modelo_vehi", nullable = false)
    private String modeloVehiculo;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "placas", nullable = false, unique = true)
    private String placas;

    @Column(name = "no_serie", nullable = false, unique = true)
    private String noSerie;

    @Column(name = "tipo_llanta", nullable = false)
    private String tipoLlanta;

    @PrePersist
    private void prePersist() {
        if (estado == null) {
            estado = "Activa";
        }
    }
}
