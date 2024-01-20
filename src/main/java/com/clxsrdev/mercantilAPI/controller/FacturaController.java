package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Factura;
import com.clxsrdev.mercantilAPI.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> getAll() {
        return facturaService.getFacturas();
    }

    @GetMapping("/{facturaId}")
    public Optional<Factura> getById(@PathVariable("/facturaId") Long id) {
        return facturaService.getFactura(id);
    }

    @GetMapping("/folio/{folio}")
    public Factura getFacturaByFolio(@PathVariable String folio) {
        return facturaService.getFacturaByFolio(folio)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada para el folio: " + folio));
    }

    @PostMapping
    public Factura saveUpdate(@RequestBody Factura factura) {
        facturaService.saveOrUpdate(factura);
        return factura;
    }

    @DeleteMapping("/{facturaId}")
    public void delete(@PathVariable("facturaId") Long id) {
        facturaService.delete(id);
    }
}
