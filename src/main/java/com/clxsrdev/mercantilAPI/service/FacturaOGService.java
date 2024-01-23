package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaMonta;
import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.repository.FacturaOGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaOGService {

    @Autowired
    FacturaOGRepository facturaOGRepository;

    public List<FacturaOG> getFacturas() {
        return facturaOGRepository.findAll();
    }

    public Optional<FacturaOG> getFactura(Long id) {
        return facturaOGRepository.findById(id);
    }

    public void saveOrUpdate(FacturaOG facturaOG) {
        facturaOGRepository.save(facturaOG);
    }

    public void delete(Long id) {
        facturaOGRepository.deleteById(id);
    }

    public List<FacturaOG> getFacturasOGBySucursal(String nombreSucursal) {
        return facturaOGRepository.getFacturasOGBySucursal(nombreSucursal);
    }

    public List<FacturaOG> getFacturasOGBySucursalAndFecha(String nombreSucursal, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturaOGRepository.getFacturasOGBySucursalAndFecha(nombreSucursal, fechaInicio, fechaFin);
    }

    public void deleteFacturaOGByFacturaId(Long idFactura) {
        FacturaOG facturaOG = facturaOGRepository.findByFacturaIdFactura(idFactura)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró FacturaOG con ID de factura: " + idFactura));
        facturaOGRepository.delete(facturaOG);
    }

    public List<Object> getFacturasOGByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return facturaOGRepository.getFacturasOGByFecha(fechaInicio, fechaFin);
    }
}
