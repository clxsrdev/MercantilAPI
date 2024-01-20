package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalRepository extends JpaRepository <Sucursal, Long> {

    Sucursal findByNombreSucursal(String nombre);
    List<Sucursal> findByEstadoSucursal(String estadoSucursal);
}
