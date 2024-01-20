package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fact -ura")
    private Long idFactura;

    @Column(name = "folio_factura", nullable = false, unique = true)
    private String folioFactura;

    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;
}
