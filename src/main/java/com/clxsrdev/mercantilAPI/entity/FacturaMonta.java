package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "facturas_montas")
public class FacturaMonta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cns_monta")
    private Long cnsMonta;

    @Column(name = "desc_fmonta", nullable = false)
    private String descripcionFacturaMonta;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_montacargas", nullable = false)
    private Montacargas idMontacargas;
}
