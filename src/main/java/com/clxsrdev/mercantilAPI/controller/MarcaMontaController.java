package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.marcaMonta;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import com.clxsrdev.mercantilAPI.service.MarcaMontaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mercantilapi/marcamonta")
public class MarcaMontaController {

    @Autowired
    private MarcaMontaService marcaMontaService;

    @GetMapping
    public List<marcaMonta> getAll(){
        return marcaMontaService.getMarcasMontas();
    }

    @GetMapping("/{marcaMontaId}")
    public Optional<marcaMonta> getById(@PathVariable("marcaMontaId") Long marcaMontaId) {
        return marcaMontaService.getMarcaMonta(marcaMontaId);
    }

    @GetMapping("/activas")
    public List<marcaMonta> getMarcasActivas() {
        return marcaMontaService.getMarcasActivas();
    }

    @GetMapping("/nombre/{marcaMontaNombre}")
    public marcaMonta getByNombre(@PathVariable("marcaMontaNombre") String marcaMontaNombre){
        return marcaMontaService.getMarcaMontaNombre(marcaMontaNombre);
    }

    @PostMapping
    public marcaMonta saveUpdate(@RequestBody marcaMonta marcaMonta) {
        marcaMontaService.saveOrUpdate(marcaMonta);
        return marcaMonta;
    }

    @PutMapping("/darDeBaja/{nombreMarca}")
    public ResponseEntity<String> darDeBajaMarcaMontaPorNombre(@PathVariable("nombreMarca") String nombreMarca) {
        marcaMonta marcaMonta = marcaMontaService.getMarcaMontaNombre(nombreMarca);

        if (marcaMonta != null) {
            marcaMonta.setEstadoMarcaMonta("Baja");
            marcaMontaService.saveOrUpdate(marcaMonta);

            return new ResponseEntity<>("Marca de monta dada de baja exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la marca de monta con el nombre proporcionado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{marcaMontaId}")
    public void delete(@PathVariable("marcaMontaId") Long marcaMontaId) {
        marcaMontaService.delete(marcaMontaId);
    }
}
