package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Factura;
import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.service.FacturaOGService;
import com.clxsrdev.mercantilAPI.service.FacturaService;
import com.clxsrdev.mercantilAPI.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/facturaog")
public class FacturaOGController {

    @Autowired
    private FacturaOGService facturaOGService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<FacturaOG> getAll() {
        return facturaOGService.getFacturas();
    }

    @GetMapping("/{id}")
    public Optional<FacturaOG> getById(@PathVariable("id")Long id) {
        return facturaOGService.getFactura(id);
    }

    @PostMapping
    public FacturaOG saveUpdate(@RequestBody Map<String, Object> payload) {
        if (payload.containsKey("descripcionFacturaOG") &&
                payload.containsKey("idFactura") &&
                payload.containsKey("idsucursal")) {

            String descripcionFacturaOG = (String) payload.get("descripcionFacturaOG");
            Long idFactura = Long.valueOf(payload.get("idFactura").toString());
            Long idSucursal = Long.valueOf(payload.get("idsucursal").toString());

            FacturaOG facturaOG = new FacturaOG();
            facturaOG.setDescripcionFacturaOG(descripcionFacturaOG);

            Factura factura = facturaService.getFactura(idFactura).orElseThrow(() -> new IllegalArgumentException("La factura no existe"));
            Sucursal sucursal = sucursalService.getSucursal(idSucursal).orElseThrow(() -> new IllegalArgumentException("La sucursal no existe"));

            facturaOG.setFactura(factura);
            facturaOG.setSucursal(sucursal);

            facturaOGService.saveOrUpdate(facturaOG);

            return facturaOG;
        } else {
            throw new IllegalArgumentException("Se requieren la descripción, idFactura e idsucursal.");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {
        facturaOGService.delete(id);
    }
}
