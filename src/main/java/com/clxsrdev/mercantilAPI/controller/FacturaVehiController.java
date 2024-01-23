package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Factura;
import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import com.clxsrdev.mercantilAPI.entity.Vehiculos;
import com.clxsrdev.mercantilAPI.request.FacturaVehiculoRequest;
import com.clxsrdev.mercantilAPI.service.FacturaService;
import com.clxsrdev.mercantilAPI.service.FacturaVehiService;
import com.clxsrdev.mercantilAPI.service.VehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/facturav")
public class FacturaVehiController {

    @Autowired
    private FacturaVehiService facturaVehiService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private VehiculosService vehiculosService;

    @GetMapping
    public List<FacturaVehiculo> getAll() {
        return facturaVehiService.getFactusV();
    }

    @GetMapping("/{factusvId}")
    public Optional<FacturaVehiculo> getById(@PathVariable("factusvId") Long id) {
        return facturaVehiService.getFacturaV(id);
    }

    @GetMapping("/nombre/{nombreVehiculo}")
    public ResponseEntity<List<FacturaVehiculo>> getFacturasVehiculoPorNombre(
            @PathVariable String nombreVehiculo) {
        List<FacturaVehiculo> facturasVehiculo = facturaVehiService.getFacturasVehiculoPorNombre(nombreVehiculo);
        return ResponseEntity.ok(facturasVehiculo);
    }

    @GetMapping("/fecha-inicio/{fechaIni}/fecha-fin/{fechaFin}")
    public ResponseEntity<List<Object>> getFacturasVehiculoByFecha(
            @PathVariable("fechaIni") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @PathVariable("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<Object> facturas = facturaVehiService.getFacturasVehiculoByFecha(fechaInicio, fechaFin);

        if (facturas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(facturas, HttpStatus.OK);
        }
    }

    @GetMapping("/nombre/{nombreVehiculo}/fecha-ini/{fechaInicio}/fecha-fin/{fechaFin}")
    public ResponseEntity<List<FacturaVehiculo>> getFacturasVehiculoPorFecha(
            @PathVariable String nombreVehiculo,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        List<FacturaVehiculo> facturasVehiculo = facturaVehiService.getFacturasVehiculoPorFecha(nombreVehiculo, fechaInicio, fechaFin);
        return ResponseEntity.ok(facturasVehiculo);
    }

    @PostMapping
    public FacturaVehiculo saveUpdate(@RequestBody Map<String, Object> payload) {
        if (payload.containsKey("descripcionFacturaVehiculo") &&
                payload.containsKey("idFactura") &&
                payload.containsKey("idVehiculo")) {

            String descripcionFacturaVehiculo = (String) payload.get("descripcionFacturaVehiculo");
            Long idFactura = Long.valueOf(payload.get("idFactura").toString());
            Long idVehiculo = Long.valueOf(payload.get("idVehiculo").toString());

            FacturaVehiculo facturaVehiculo = new FacturaVehiculo();
            facturaVehiculo.setDescripcionFacturaVehiculo(descripcionFacturaVehiculo);

            Factura factura = facturaService.getFactura(idFactura).orElseThrow(() -> new IllegalArgumentException("La factura no existe"));
            Vehiculos vehiculo = vehiculosService.getVehiculo(idVehiculo).orElseThrow(() -> new IllegalArgumentException("El vehículo no existe"));

            facturaVehiculo.setFactura(factura);
            facturaVehiculo.setIdVehiculo(vehiculo);

            facturaVehiService.saveOrUpdate(facturaVehiculo);

            return facturaVehiculo;
        } else {
            throw new IllegalArgumentException("Se requieren la descripción, idFactura y idVehiculo.");
        }
    }

    @DeleteMapping("/{factusvId}")
    public void delete(@PathVariable("factusvId") Long id) {
        facturaVehiService.delete(id);
    }

    @DeleteMapping("/id/{idFactura}")
    public ResponseEntity<?> deleteFacturaVehiculo(@PathVariable Long idFactura) {
        try {
            facturaVehiService.deleteFacturaVehiculoByIdFactura(idFactura);
            return ResponseEntity.ok("Factura Vehiculo eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al intentar eliminar la Factura Vehiculo.");
        }
    }
}
