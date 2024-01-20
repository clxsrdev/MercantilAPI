package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.marcaMonta;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MarcaMontaRepository extends JpaRepository <marcaMonta, Long>{
    marcaMonta findByNombreMarca(String nombre);
    List<marcaMonta> findByEstadoMarcaMonta(String estadoMarcaMonta);

}
