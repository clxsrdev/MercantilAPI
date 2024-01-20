package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.Montacargas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MontacargasRepository extends JpaRepository<Montacargas, Long> {

    Montacargas findByNombre(String nombreMontacargas);

    List<Montacargas> findByEstado(String estado);

}
