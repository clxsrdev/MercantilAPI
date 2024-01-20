package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import com.clxsrdev.mercantilAPI.repository.MarcaMontaRepository;
import com.clxsrdev.mercantilAPI.repository.MarcaVehiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaVehiService {

    @Autowired
    MarcaVehiRepository marcaVehiRepository;

    public List<marcaVehiculo> getMarcasVehiculos() {
        return marcaVehiRepository.findAll();
    }

    public marcaVehiculo getMarcaVehiculoNombre(String nombre) {
        return marcaVehiRepository.findByNombreMarcaVehiculo(nombre);
    }

    public List<marcaVehiculo> getMarcasActivas() {
        return marcaVehiRepository.findByEstadoMarcaVehiculo("Activa");
    }

    public Optional<marcaVehiculo> getMarcaVehiculo(Long id) {
        return marcaVehiRepository.findById(id);
    }

    public void saveOrUpdate(marcaVehiculo marcaVehiculo) {
        marcaVehiRepository.save(marcaVehiculo);
    }

    public void delete(Long id) {
        marcaVehiRepository.deleteById(id);
    }
}

