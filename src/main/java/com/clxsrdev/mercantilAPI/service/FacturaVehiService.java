package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import com.clxsrdev.mercantilAPI.repository.FacturaVehiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaVehiService {

    @Autowired
    FacturaVehiRepository facturaVehiRepository;

    public List<FacturaVehiculo> getFactusV() {
        return facturaVehiRepository.findAll();
    }

    public Optional<FacturaVehiculo> getFacturaV(Long id) {
        return facturaVehiRepository.findById(id);
    }

    public void saveOrUpdate(FacturaVehiculo facturaVehiculo) {
        facturaVehiRepository.save(facturaVehiculo);
    }

    public void delete(Long id) {
        facturaVehiRepository.deleteById(id);
    }

    public List<FacturaVehiculo> getFacturasVehiculoPorNombre(String nombreVehiculo) {
        // Implementa la lógica para obtener facturas por nombre
        return facturaVehiRepository.findByNombreVehiculo(nombreVehiculo);
    }

    public List<Object> getFacturasVehiculoByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        // Lógica para obtener las facturas de vehículos por el rango de fechas
        return facturaVehiRepository.getFacturasVehiculoByFecha(fechaInicio, fechaFin);
    }

    public List<FacturaVehiculo> getFacturasVehiculoPorFecha(String nombreVehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementa la lógica para obtener facturas por nombre y rango de fechas
        return facturaVehiRepository.findByNombreVehiculoAndFechaBetween(nombreVehiculo, fechaInicio, fechaFin);
    }

    public void deleteFacturaVehiculoByIdFactura(Long idFactura) {
        FacturaVehiculo facturaVehiculo = facturaVehiRepository.findByFacturaIdFactura(idFactura)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró Factura Vehiculo con ID de factura: " + idFactura));

        facturaVehiRepository.delete(facturaVehiculo);
    }
}
