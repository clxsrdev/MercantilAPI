package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marcamonta")
public class marcaMonta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mm")
    private Long idMarcaMonta;

    @Column(name = "nombre_mm", nullable = false, unique = true)
    private String nombreMarca;

    @Column(name = "estado_mm", nullable = false)
    private String estadoMarcaMonta;

    @PrePersist
    private void prePersist() {
        if (estadoMarcaMonta == null) {
            estadoMarcaMonta = "Activa";
        }
    }
}
