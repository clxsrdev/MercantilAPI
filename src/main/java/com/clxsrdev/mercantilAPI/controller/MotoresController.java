package com.clxsrdev.mercantilAPI.controller;

import com.clxsrdev.mercantilAPI.entity.Motores;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.service.MotoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercantilapi/motores")
public class MotoresController {

    @Autowired
    private MotoresService motoresService;

    @GetMapping
    public List<Motores> getAll() {
        return motoresService.getMotores();
    }

    @GetMapping("/{motorId}")
    public Optional<Motores> getById(@PathVariable("motorId") Long id){
        return motoresService.getMotor(id);
    }

    @GetMapping("/activas")
    public List<Motores> getMotoresActivos() {
        return motoresService.getMotorActivos();
    }

    @GetMapping("/nombre/{motorNombre}")
    public Motores getByNombre(@PathVariable("motorNombre") String motorNombre) {
        return motoresService.getMotorNombre(motorNombre);
    }

    @PostMapping
    public Motores saveUpdate(@RequestBody Motores motores) {
        motoresService.saveOrUpdate(motores);
        return motores;
    }

    @PutMapping("/darDeBaja/{nombreMotor}")
    public ResponseEntity<String> darDeBajaMotorPorNombre(@PathVariable("nombreMotor") String nombreMotor) {
        Motores motor = motoresService.getMotorNombre(nombreMotor);

        if (motor != null) {
            motor.setEstadoMotor("Baja");
            motoresService.saveOrUpdate(motor);
            return new ResponseEntity<>("Motor dado de baja exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el motor con nombre " + nombreMotor, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{motorId}")
    public void delete(@PathVariable("motorId") Long id) {
        motoresService.delete(id);
    }
}
