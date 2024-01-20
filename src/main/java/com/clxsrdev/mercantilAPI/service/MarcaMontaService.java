package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.marcaMonta;
import com.clxsrdev.mercantilAPI.entity.marcaVehiculo;
import com.clxsrdev.mercantilAPI.repository.MarcaMontaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaMontaService {

    @Autowired
    MarcaMontaRepository marcaMontaRepository;

    public List<marcaMonta> getMarcasMontas() {
        return marcaMontaRepository.findAll();
    }

    public marcaMonta getMarcaMontaNombre(String nombre){
        return marcaMontaRepository.findByNombreMarca(nombre);
    }

    public List<marcaMonta> getMarcasActivas() {
        return marcaMontaRepository.findByEstadoMarcaMonta("Activa");
    }


    public Optional<marcaMonta> getMarcaMonta(Long id){
        return marcaMontaRepository.findById(id);
    }

    public void saveOrUpdate(marcaMonta marcaMonta) {
        marcaMontaRepository.save(marcaMonta);
    }

    public void delete(Long id) {
        marcaMontaRepository.deleteById((id));
    }
}
