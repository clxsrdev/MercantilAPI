package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.entity.Vehiculos;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import com.clxsrdev.mercantilAPI.service.MarcaVehiService;
import com.clxsrdev.mercantilAPI.service.SucursalService;
import com.clxsrdev.mercantilAPI.service.VehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/vehiculos")
public class VehiculosController {

    @Autowired
    private VehiculosService vehiculosService;

    @Autowired
    private MarcaVehiService marcaVehiculoService;

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Vehiculos> getAll() {
        return vehiculosService.getVehiculos();
    }

    @GetMapping("/{vehiculosId}")
    public Optional<Vehiculos> getById(@PathVariable("vehiculosId") Long id) {
        return vehiculosService.getVehiculo(id);
    }

    @PostMapping
    public ResponseEntity<Vehiculos> saveUpdate(@RequestBody Map<String, Object> vehiculoData) {
        String nombreMarcaVehiculo = (String) vehiculoData.get("nombreMarcaVehiculo");
        String nombreSucursal = (String) vehiculoData.get("nombreSucursal");

        marcaVehiculo marcaVehiculo = marcaVehiculoService.getMarcaVehiculoNombre(nombreMarcaVehiculo);
        if (marcaVehiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Sucursal sucursal = sucursalService.getSucursalNombre(nombreSucursal);
            if (sucursal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Vehiculos vehiculos = new Vehiculos();
        vehiculos.setMarcaVehiculo(marcaVehiculo);
        vehiculos.setSucursal(sucursal);

        vehiculos.setNombre((String) vehiculoData.get("nombreVehiculo"));
        vehiculos.setModeloVehiculo((String) vehiculoData.get("modeloVehiculo"));
        vehiculos.setEstado((String) vehiculoData.get("estado"));
        vehiculos.setPlacas((String) vehiculoData.get("placas"));
        vehiculos.setNoSerie((String) vehiculoData.get("noSerie"));
        vehiculos.setTipoLlanta((String) vehiculoData.get("tipoLlanta"));

        vehiculosService.saveOrUpdate(vehiculos);

        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Vehiculos> getVehiculoByNombre(@PathVariable String nombre) {
        Optional<Vehiculos> vehiculo = vehiculosService.getVehiculoByNombre(nombre);

        return vehiculo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Vehiculos>> getVehiculosActivos() {
        List<Vehiculos> vehiculosActivos = vehiculosService.getVehiculosActivos();

        if (!vehiculosActivos.isEmpty()) {
            return new ResponseEntity<>(vehiculosActivos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/darDeBaja/{nombre}")
    public ResponseEntity<String> darDeBajaVehiculoPorNombre(@PathVariable String nombre) {
        vehiculosService.darDeBajaVehiculoPorNombre(nombre);

        return new ResponseEntity<>("Vehículo dado de baja exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{vehiculoId}")
    public void delete(@PathVariable("vehiculoId") Long id) {
        vehiculosService.delete(id);
    }
}
