package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "facturas_otros")
public class FacturaOG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cns_og")
    private Long cnsOG;

    @Column(name = "desc_og", nullable = false)
    private String descripcionFacturaOG;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idsucursal", nullable = false)
    private Sucursal sucursal;
}
