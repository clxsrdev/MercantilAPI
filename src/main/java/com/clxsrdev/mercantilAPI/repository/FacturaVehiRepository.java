package com.clxsrdev.mercantilAPI.repository;

import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FacturaVehiRepository extends JpaRepository<FacturaVehiculo, Long> {

    @Query("SELECT fv FROM FacturaVehiculo fv WHERE fv.factura.idFactura = :idFactura")
    Optional<FacturaVehiculo> findByFacturaIdFactura(Long idFactura);

    @Query("SELECT fv FROM FacturaVehiculo fv JOIN fv.idVehiculo v WHERE v.nombre = :nombreVehiculo")
    List<FacturaVehiculo> findByNombreVehiculo(@Param("nombreVehiculo") String nombreVehiculo);

    @Query("SELECT fv FROM FacturaVehiculo fv JOIN fv.idVehiculo v JOIN fv.factura f " +
            "WHERE v.nombre = :nombreVehiculo AND f.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<FacturaVehiculo> findByNombreVehiculoAndFechaBetween(
            @Param("nombreVehiculo") String nombreVehiculo,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT fv FROM FacturaVehiculo fv WHERE fv.idVehiculo.sucursal.nombreSucursal = :nombreSucursal")
    List<FacturaVehiculo> getFacturasVehiculoBySucursal(@Param("nombreSucursal") String nombreSucursal);

    @Query("SELECT fv FROM FacturaVehiculo fv WHERE fv.idVehiculo.sucursal.nombreSucursal = :nombreSucursal AND fv.factura.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<FacturaVehiculo> getFacturasVehiculoBySucursalAndFecha(
            @Param("nombreSucursal") String nombreSucursal,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    @Query("SELECT fv FROM FacturaVehiculo fv JOIN fv.factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Object> getFacturasVehiculoByFecha(LocalDate fechaInicio, LocalDate fechaFin);

}
