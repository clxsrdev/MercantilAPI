package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.Motores;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.repository.MotoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoresService {

    @Autowired
    MotoresRepository motoresRepository;

    public List<Motores> getMotores() {
        return motoresRepository.findAll();
    }

    public Motores getMotorNombre(String nombre){
        return motoresRepository.findByDescripcionMotor(nombre);
    }

    public List<Motores> getMotorActivos() {
        return motoresRepository.findByEstadoMotor("Activa");
    }

    public Optional<Motores> getMotor(Long id) {
        return motoresRepository.findById(id);
    }

    public void saveOrUpdate(Motores motores) {
        motoresRepository.save(motores);
    }

    public void delete(Long id) {
        motoresRepository.deleteById(id);
    }
}
