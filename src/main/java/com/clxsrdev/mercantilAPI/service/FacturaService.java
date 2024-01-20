package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.Factura;
import com.clxsrdev.mercantilAPI.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaByFolio(String folio) {
        return facturaRepository.findByFolioFactura(folio);
    }

    public Optional<Factura> getFactura(Long id) {
        return facturaRepository.findById(id);
    }

    public void saveOrUpdate(Factura factura) {
        facturaRepository.save(factura);
    }

    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }
}
