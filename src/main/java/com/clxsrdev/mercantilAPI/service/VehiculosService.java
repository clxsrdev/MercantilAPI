package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.Vehiculos;
import com.clxsrdev.mercantilAPI.repository.VehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculosService {

    @Autowired
    VehiculosRepository vehiculosRepository;

    public List<Vehiculos> getVehiculos() {
        return vehiculosRepository.findAll();
    }

    public Optional<Vehiculos> getVehiculo(Long id) {
        return vehiculosRepository.findById(id);
    }

    public void saveOrUpdate(Vehiculos vehiculos) {
        vehiculosRepository.save(vehiculos);
    }

    public Optional<Vehiculos> getVehiculoByNombre(String nombre) {
        return vehiculosRepository.findByNombre(nombre);
    }

    public List<Vehiculos> getVehiculosActivos() {
        return vehiculosRepository.findByEstado("Activa");
    }

    public void darDeBajaVehiculoPorNombre(String nombre) {
        vehiculosRepository.findByNombre(nombre).ifPresent(vehiculo -> {
            vehiculo.setEstado("Baja");
            vehiculosRepository.save(vehiculo);
        });
    }

    public void delete(Long id) {
        vehiculosRepository.deleteById(id);
    }
}
