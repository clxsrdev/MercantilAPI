package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.Montacargas;
import com.clxsrdev.mercantilAPI.repository.MontacargasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MontacargasService {

    @Autowired
    MontacargasRepository montacargasRepository;

    public List<Montacargas> getMontacargas() {
        return montacargasRepository.findAll();
    }

    public Optional<Montacargas> getMontacarga(Long id) {
        return montacargasRepository.findById(id);
    }

    public void saveOrUpdate(Montacargas montacargas) {
        montacargasRepository.save(montacargas);
    }

    public void delete(Long id) {
        montacargasRepository.deleteById(id);
    }

    public List<Montacargas> getMontacargasActivos() {
        return montacargasRepository.findByEstado("Activa");
    }

    public Montacargas findByNombre(String nombreMontacargas) {
        return montacargasRepository.findByNombre(nombreMontacargas);
    }
}
