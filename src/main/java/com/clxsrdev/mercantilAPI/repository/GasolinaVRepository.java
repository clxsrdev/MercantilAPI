package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.GasolinaV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GasolinaVRepository extends JpaRepository<GasolinaV, Long> {

    @Query("SELECT g FROM GasolinaV g WHERE g.idVehiculo.nombre = :nombre")
    List<GasolinaV> findByNombreVehiculo(String nombre);

    @Query("SELECT g FROM GasolinaV g WHERE g.idVehiculo.nombre = :nombre AND g.fechaCarga BETWEEN :fechaInicio AND :fechaFin")
    List<GasolinaV> findByNombreVehiculoAndFechaCargaBetween(String nombre, LocalDate fechaInicio, LocalDate fechaFin);

}
