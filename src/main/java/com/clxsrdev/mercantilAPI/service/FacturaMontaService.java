package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaMonta;
import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.repository.FacturaMontaRepository;
import com.clxsrdev.mercantilAPI.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaMontaService {

    @Autowired
    FacturaMontaRepository facturaMontaRepository;

    public List<FacturaMonta> getFactusM() {
        return facturaMontaRepository.findAll();
    }

    public Optional<FacturaMonta> getFacturaM(Long id) {
        return facturaMontaRepository.findById(id);
    }

    public void saveorUpdate(FacturaMonta facturaMonta) {
        facturaMontaRepository.save(facturaMonta);
    }

    public void delete(Long id) {
        facturaMontaRepository.deleteById(id);
    }

    public void deleteFacturaMontaByFacturaId(Long idFactura) {
        FacturaMonta facturaMonta = facturaMontaRepository.findByFacturaIdFactura(idFactura)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro FacturaMonta con el ID de factura: " + idFactura));
        facturaMontaRepository.delete(facturaMonta);
    }
}
