package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Factura;
import com.clxsrdev.mercantilAPI.entity.FacturaMonta;
import com.clxsrdev.mercantilAPI.entity.Montacargas;
import com.clxsrdev.mercantilAPI.service.FacturaMontaService;
import com.clxsrdev.mercantilAPI.service.FacturaService;
import com.clxsrdev.mercantilAPI.service.MontacargasService;
import com.clxsrdev.mercantilAPI.service.VehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/facturam")
public class FacturaMontaController {

    @Autowired
    private FacturaMontaService facturaMontaService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private MontacargasService montacargasService;

    @GetMapping
    public List<FacturaMonta> getAll() {
        return facturaMontaService.getFactusM();
    }

    @GetMapping("/{factumId}")
    public Optional<FacturaMonta>  getById(@PathVariable("factumId") Long id) {
        return facturaMontaService.getFacturaM(id);
    }

    @PostMapping
    public FacturaMonta saveUpdate(@RequestBody Map<String, Object> payload) {
        if (payload.containsKey("descripcionFacturaMonta") &&
                payload.containsKey("idFactura") &&
                payload.containsKey("idMontacargas")) {

            String descripcionFacturaMonta = (String) payload.get("descripcionFacturaMonta");
            Long idFactura = Long.valueOf(payload.get("idFactura").toString());
            Long idMontacargas = Long.valueOf(payload.get("idMontacargas").toString());

            FacturaMonta facturaMonta = new FacturaMonta();
            facturaMonta.setDescripcionFacturaMonta(descripcionFacturaMonta);

            Factura factura = facturaService.getFactura(idFactura).orElseThrow(() -> new IllegalArgumentException("La factura no existe"));
            Montacargas montacargas = montacargasService.getMontacarga(idMontacargas).orElseThrow(() -> new IllegalArgumentException("El montacargas no existe"));

            facturaMonta.setFactura(factura);
            facturaMonta.setIdMontacargas(montacargas);

            facturaMontaService.saveorUpdate(facturaMonta);

            return facturaMonta;
        } else {
            throw new IllegalArgumentException("Se requieren la descripción, idFactura y idMontacargas.");
        }
    }


    @DeleteMapping("/{factumId}")
    public void delete(@PathVariable("factumId") Long id) {
        facturaMontaService.delete(id);
    }

    @DeleteMapping("/id/{idFactura}")
    public ResponseEntity<?> deleteFacturaMonta(@PathVariable Long idFactura) {
        try {
            facturaMontaService.deleteFacturaMontaByFacturaId(idFactura);
            return ResponseEntity.ok("Factura Monta eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al intentar eliminar la Factura Monta.");
        }
    }

}
