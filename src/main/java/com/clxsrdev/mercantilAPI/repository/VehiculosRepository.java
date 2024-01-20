package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculosRepository extends JpaRepository<Vehiculos, Long> {

    Optional<Vehiculos> findByNombre(String nombre);
    List<Vehiculos> findByEstado(String estado);
}

