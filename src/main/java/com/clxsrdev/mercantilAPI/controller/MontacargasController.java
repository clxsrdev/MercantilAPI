package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Montacargas;
import com.clxsrdev.mercantilAPI.entity.Motores;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.entity.marcaMonta;
import com.clxsrdev.mercantilAPI.service.MarcaMontaService;
import com.clxsrdev.mercantilAPI.service.MontacargasService;
import com.clxsrdev.mercantilAPI.service.MotoresService;
import com.clxsrdev.mercantilAPI.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/montas")
public class MontacargasController {

    @Autowired
    private MarcaMontaService marcaMontaService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private MontacargasService montacargasService;

    @Autowired
    private MotoresService motoresService;

    @GetMapping
    public List<Montacargas> getAll() {
        return montacargasService.getMontacargas();
    }

    @GetMapping("/{montaId}")
    public Optional<Montacargas> getById(@PathVariable("montaId") Long id) {
        return montacargasService.getMontacarga(id);
    }

    @PostMapping
    public ResponseEntity<Montacargas> saveMontacargas(@RequestBody Map<String, Object> montacargasData) {
        String nombreMarcaMonta = (String) montacargasData.get("nombreMarcaMonta");
        String nombreSucursal = (String) montacargasData.get("nombreSucursal");
        String descripcionMotor = (String) montacargasData.get("descripcionMotor");

        marcaMonta marcaMonta = marcaMontaService.getMarcaMontaNombre(nombreMarcaMonta);
        if (marcaMonta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Sucursal sucursal = sucursalService.getSucursalNombre(nombreSucursal);
        if (sucursal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Motores motor = motoresService.getMotorNombre(descripcionMotor);
        if (motor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Montacargas montacargas = new Montacargas();
        montacargas.setMarcaMonta(marcaMonta);
        montacargas.setSucursal(sucursal);
        montacargas.setMotor(motor);

        montacargas.setNombre((String) montacargasData.get("nombre"));
        montacargas.setModelo((String) montacargasData.get("modelo"));
        montacargas.setCapacidad((int) montacargasData.get("capacidad"));
        montacargas.setNoEconomico((String) montacargasData.get("noEconomico"));
        montacargas.setSerie((String) montacargasData.get("serie"));
        montacargas.setEstado((String) montacargasData.get("estado"));
        montacargas.setCargaMaxima((int) montacargasData.get("cargaMaxima"));

        montacargasService.saveOrUpdate(montacargas);

        return ResponseEntity.ok(montacargas);
    }

    @GetMapping("/nombre/{nombreMontacargas}")
    public ResponseEntity<Montacargas> getMontacargasByNombre(@PathVariable("nombreMontacargas") String nombreMontacargas) {
        Montacargas montacargas = montacargasService.findByNombre(nombreMontacargas);

        if (montacargas != null) {
            return new ResponseEntity<>(montacargas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/darDeBaja/{nombreMontacargas}")
    public ResponseEntity<String> darDeBajaMontacargas(@PathVariable("nombreMontacargas") String nombreMontacargas) {
        Montacargas montacargas = montacargasService.findByNombre(nombreMontacargas);

        if (montacargas != null) {
            montacargas.setEstado("Baja");
            montacargasService.saveOrUpdate(montacargas);
            return new ResponseEntity<>("Montacargas '" + nombreMontacargas + "' dado de baja exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Montacargas '" + nombreMontacargas + "' no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Montacargas>> getMontacargasActivos() {
        List<Montacargas> montacargasActivos = montacargasService.getMontacargasActivos();

        if (!montacargasActivos.isEmpty()) {
            return new ResponseEntity<>(montacargasActivos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{montaId}")
    public void delete(@PathVariable("montaId") Long id) {
        montacargasService.delete(id);
    }
}
