package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaMonta;
import com.clxsrdev.mercantilAPI.entity.FacturaOG;
import com.clxsrdev.mercantilAPI.repository.FacturaOGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaOGService {

    @Autowired
    FacturaOGRepository facturaOGRepository;

    public List<FacturaOG> getFacturas() {
        return facturaOGRepository.findAll();
    }

    public Optional<FacturaOG> getFactura(Long id) {
        return facturaOGRepository.findById(id);
    }

    public void saveOrUpdate(FacturaOG facturaOG) {
        facturaOGRepository.save(facturaOG);
    }

    public void delete(Long id) {
        facturaOGRepository.deleteById(id);
    }
}
