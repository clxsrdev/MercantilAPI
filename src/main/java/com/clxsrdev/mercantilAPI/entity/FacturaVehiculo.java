package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "facturas_vehiculos")
public class FacturaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cns_vehi")
    private Long cnsVehiculo;

    @Column(name = "desc_fvehi", nullable = false)
    private String descripcionFacturaVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_vehi", nullable = false)
    private Vehiculos idVehiculo;
}
