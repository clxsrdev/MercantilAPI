package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FacturaOGRepository extends JpaRepository<FacturaOG, Long> {

    @Query("SELECT fv FROM FacturaOG fv WHERE fv.factura.idFactura = :idFactura")
    Optional<FacturaOG> findByFacturaIdFactura(Long idFactura);

    @Query("SELECT fo FROM FacturaOG fo JOIN fo.factura f JOIN fo.sucursal s WHERE s.nombreSucursal = :nombreSucursal")
    List<FacturaOG> getFacturasOGBySucursal(@Param("nombreSucursal") String nombreSucursal);

    @Query("SELECT fo FROM FacturaOG fo JOIN fo.factura f JOIN fo.sucursal s WHERE s.nombreSucursal = :nombreSucursal AND f.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<FacturaOG> getFacturasOGBySucursalAndFecha(@Param("nombreSucursal") String nombreSucursal, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT fog FROM FacturaOG fog JOIN fog.factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Object> getFacturasOGByFecha(LocalDate fechaInicio, LocalDate fechaFin);
}
