package com.clxsrdev.mercantilAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "motores")
public class Motores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motor")
    private Long idMotor;

    @Column(name = "desc_motor", nullable = false, unique = true)
    private String descripcionMotor;

    @Column(name = "estado_motor", nullable = false)
    private String estadoMotor;

    @PrePersist
    private void prePersist() {
        if (estadoMotor == null) {
            estadoMotor = "Activa";
        }
    }
}