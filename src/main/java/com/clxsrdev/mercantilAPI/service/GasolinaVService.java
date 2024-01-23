package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.GasolinaV;
import com.clxsrdev.mercantilAPI.repository.GasolinaVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GasolinaVService {

    @Autowired
    GasolinaVRepository gasolinaVRepository;

    public List<GasolinaV> getAll() {
        return gasolinaVRepository.findAll();
    }

    public Optional<GasolinaV> getById(Long id) {
        return gasolinaVRepository.findById(id);
    }

    public void saveOrUpdate(GasolinaV gasolinaV) {
        gasolinaVRepository.save(gasolinaV);
    }

    public void delete(Long id) {
        gasolinaVRepository.deleteById(id);
    }

    public List<GasolinaV> getGasolinaVehiculoByNombre(String nombre) {
        return gasolinaVRepository.findByNombreVehiculo(nombre);
    }

    public List<GasolinaV> getGasolinaVehiculoByNombreYFecha(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        return gasolinaVRepository.findByNombreVehiculoAndFechaCargaBetween(nombre, fechaInicio, fechaFin);
    }
}
