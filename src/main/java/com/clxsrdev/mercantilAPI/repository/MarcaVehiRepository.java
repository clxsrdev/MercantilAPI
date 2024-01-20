package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MarcaVehiRepository extends JpaRepository<marcaVehiculo, Long>{
    marcaVehiculo findByNombreMarcaVehiculo(String nombre);
    List<marcaVehiculo> findByEstadoMarcaVehiculo(String estadoMarcaVehiculo);

}
