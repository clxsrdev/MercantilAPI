package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.Motores;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoresRepository extends JpaRepository<Motores, Long> {

    Motores findByDescripcionMotor(String nombre);
    List<Motores> findByEstadoMotor(String estadoMotor);

}
