package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.GasolinaV;
import com.clxsrdev.mercantilAPI.entity.Vehiculos;
import com.clxsrdev.mercantilAPI.request.GasolinaVRequest;
import com.clxsrdev.mercantilAPI.service.GasolinaVService;
import com.clxsrdev.mercantilAPI.service.VehiculosService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/gasv")
public class GasolinaVController {

    @Autowired
    private GasolinaVService gasolinaVService;

    @Autowired
    private VehiculosService vehiculosService;

    @GetMapping
    List<GasolinaV> getAll() {
        return gasolinaVService.getAll();
    }

    @GetMapping("/{id}")
    Optional<GasolinaV> getById(@PathVariable("id") Long id) {
        return gasolinaVService.getById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<GasolinaV> getGasolinaVehiculoByNombre(@PathVariable String nombre) {
        return gasolinaVService.getGasolinaVehiculoByNombre(nombre);
    }

    @GetMapping("/nombre/{nombre}/fecha-inicio/{fechaIni}/fecha-fin/{fechaFin}")
    public List<GasolinaV> getGasolinaVehiculoByNombreYFecha(
            @PathVariable String nombre,
            @PathVariable("fechaIni") LocalDate fechaInicio,
            @PathVariable("fechaFin") LocalDate fechaFin) {
        return gasolinaVService.getGasolinaVehiculoByNombreYFecha(nombre, fechaInicio, fechaFin);
    }

    @PostMapping
    public GasolinaV saveUpdate(@RequestBody GasolinaVRequest gasolinaVRequest) {
        GasolinaV gasolinaV = new GasolinaV();

        // Aquí puedes utilizar algún método para cargar el Vehiculo usando el ID
        Vehiculos vehiculo = vehiculosService.getVehiculo(gasolinaVRequest.getIdVehiculo()).orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado"));

        // Configurar el resto de los campos de GasolinaV
        gasolinaV.setIdVehiculo(vehiculo);
        gasolinaV.setRuta(gasolinaVRequest.getRuta());
        gasolinaV.setLtsGas(gasolinaVRequest.getLtsGas());
        gasolinaV.setFechaCarga(gasolinaVRequest.getFechaCarga());
        gasolinaV.setKilometraje(gasolinaVRequest.getKilometraje());
        gasolinaV.setMonto(gasolinaVRequest.getMonto());

        gasolinaVService.saveOrUpdate(gasolinaV);

        return gasolinaV;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        gasolinaVService.delete(id);
    }
}

