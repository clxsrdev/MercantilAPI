package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "gasolina_v")
public class GasolinaV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasv")
    private Long idGasVehi;

    @ManyToOne
    @JoinColumn(name = "id_vehi", nullable = false, referencedColumnName = "id_vehi")
    private Vehiculos idVehiculo;

    @Column(name = "ruta", nullable = false)
    private String ruta;

    @Column(name = "lts_gas")
    private Double ltsGas;

    @Column(name = "fecha_carga")
    private Date fechaCarga;

    @Column(name = "kilometraje")
    private Integer kilometraje;
}
