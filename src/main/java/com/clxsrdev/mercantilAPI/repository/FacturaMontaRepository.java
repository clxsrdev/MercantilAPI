package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.FacturaMonta;
import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacturaMontaRepository extends JpaRepository<FacturaMonta, Long> {

    @Query("SELECT fv FROM FacturaMonta fv WHERE fv.factura.idFactura = :idFactura")
    Optional<FacturaMonta> findByFacturaIdFactura(Long idFactura);
}
