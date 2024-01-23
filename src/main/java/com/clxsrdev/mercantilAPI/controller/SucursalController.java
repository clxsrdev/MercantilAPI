package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.repository.FacturaVehiRepository;
import com.clxsrdev.mercantilAPI.service.FacturaOGService;
import com.clxsrdev.mercantilAPI.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mercantilapi/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private FacturaOGService facturaOGService;

    @Autowired
    private FacturaVehiRepository facturaVehiculoRepository;

    @GetMapping
    public List<Sucursal> getAll() {
        return sucursalService.getSucursales();
    }

    @GetMapping("/{sucursalId}")
    public Optional<Sucursal> getById(@PathVariable("sucursalId") Long sucursalId) {
        return sucursalService.getSucursal(sucursalId);
    }

    @GetMapping("/activas")
    public List<Sucursal> getSucursalesActivas() {
        return sucursalService.getSucursalesActivas();
    }

    @GetMapping("/nombre/{sucursalNombre}")
    public Sucursal getByNombre(@PathVariable("sucursalNombre") String sucursalNombre) {
        return sucursalService.getSucursalNombre(sucursalNombre);
    }

    @GetMapping("/facturas/{nombreSucursal}")
    public ResponseEntity<List<FacturaVehiculo>> getFacturasBySucursal(@PathVariable("nombreSucursal") String nombreSucursal) {
        List<FacturaVehiculo> facturasVehiculo = facturaVehiculoRepository.getFacturasVehiculoBySucursal(nombreSucursal);
        return ResponseEntity.ok(facturasVehiculo);
    }

    @GetMapping("/facturas/{nombre}/fecha-inicio/{fechaInicio}/fecha-fin/{fechaFin}")
    public List<FacturaVehiculo> getFacturasVehiculoBySucursalAndFecha(
            @PathVariable String nombre,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        return sucursalService.getFacturasVehiculoBySucursalAndFecha(nombre, fechaInicio, fechaFin);
    }

    @GetMapping("/facturasog/{nombre}")
    public ResponseEntity<List<FacturaOG>> getFacturasOGBySucursal(@PathVariable String nombre) {
        List<FacturaOG> facturasOG = facturaOGService.getFacturasOGBySucursal(nombre);
        return new ResponseEntity<>(facturasOG, HttpStatus.OK);
    }

    @GetMapping("/facturasog/{nombre}/fecha-inicio/{fechaIni}/fecha-fin/{fechaFin}")
    public ResponseEntity<List<FacturaOG>> getFacturasOGBySucursalAndFecha(
            @PathVariable String nombre,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIni,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<FacturaOG> facturasOG = facturaOGService.getFacturasOGBySucursalAndFecha(nombre, fechaIni, fechaFin);
        return new ResponseEntity<>(facturasOG, HttpStatus.OK);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Sucursal sucursal) {
        sucursalService.saveOrUpdate(sucursal);
    }

    @PutMapping("/darDeBajaPorNombre/{nombreSucursal}")
    public ResponseEntity<String> darDeBajaSucursalPorNombre(@PathVariable("nombreSucursal") String nombreSucursal) {
        Sucursal sucursal = sucursalService.getSucursalNombre(nombreSucursal);

        if (sucursal != null ) {
            sucursal.setEstadoSucursal("Baja");
            sucursalService.saveOrUpdate(sucursal);
            return new ResponseEntity<>("Sucursal dada de baja exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la sucursal con nombre " + nombreSucursal, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{sucursalId}")
    public void delete(@PathVariable("sucursalId") Long sucursalId) {
        sucursalService.delete(sucursalId);
    }
}
