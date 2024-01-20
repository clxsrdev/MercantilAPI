package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import com.clxsrdev.mercantilAPI.service.MarcaVehiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/marcavehiculo")
public class MarcaVehiController {

    @Autowired
    private MarcaVehiService marcaVehiService;

    @GetMapping
    public List<marcaVehiculo> getAll() {
        return marcaVehiService.getMarcasVehiculos();
    }

    @GetMapping("/{marcaVehiId}")
    public Optional<marcaVehiculo> getById(@PathVariable("marcaVehiId") Long marcaVehiId) {
        return marcaVehiService.getMarcaVehiculo(marcaVehiId);
    }

    @GetMapping("/activas")
    public List<marcaVehiculo> getMarcasActivas() {
        return marcaVehiService.getMarcasActivas();
    }

    @GetMapping("/nombre/{marcaVehiNombre}")
    public marcaVehiculo getByNombre(@PathVariable("marcaVehiNombre") String nombre) {
        return marcaVehiService.getMarcaVehiculoNombre(nombre);
    }

    @PostMapping
    public marcaVehiculo saveUpdate(@RequestBody marcaVehiculo marcaVehiculo) {
        marcaVehiService.saveOrUpdate(marcaVehiculo);
        return marcaVehiculo;
    }

    @PutMapping("/darDeBaja/{marcaVehiNombre}")
    public ResponseEntity<String> darDeBajaPorNombre(@PathVariable("marcaVehiNombre") String nombre) {
        marcaVehiculo marca = marcaVehiService.getMarcaVehiculoNombre(nombre);
        if (marca != null) {
            marca.setEstadoMarcaVehiculo("Baja");
            marcaVehiService.saveOrUpdate(marca);
            return new ResponseEntity<>("La marca de vehículo se dio de baja exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la marca de vehículo con el nombre proporcionado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{marcaVehiId}")
    public void delete(@PathVariable("marcaVehiId") Long id) {
        marcaVehiService.delete(id);
    }
}
