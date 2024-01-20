package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mercantilapi/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

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
