package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import com.clxsrdev.mercantilAPI.repository.FacturaVehiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteFacturaVehiculoByIdFactura(Long idFactura) {
        FacturaVehiculo facturaVehiculo = facturaVehiRepository.findByFacturaIdFactura(idFactura)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró Factura Vehiculo con ID de factura: " + idFactura));

        facturaVehiRepository.delete(facturaVehiculo);
    }
}
