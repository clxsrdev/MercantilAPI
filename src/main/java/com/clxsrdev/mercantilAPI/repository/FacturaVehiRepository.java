package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacturaVehiRepository extends JpaRepository<FacturaVehiculo, Long> {

    @Query("SELECT fv FROM FacturaVehiculo fv WHERE fv.factura.idFactura = :idFactura")
    Optional<FacturaVehiculo> findByFacturaIdFactura(Long idFactura);
}
