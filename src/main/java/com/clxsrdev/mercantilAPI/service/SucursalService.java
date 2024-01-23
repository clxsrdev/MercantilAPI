package com.clxsrdev.mercantilAPI.service;

import com.clxsrdev.mercantilAPI.entity.FacturaVehiculo;
import com.clxsrdev.mercantilAPI.entity.Sucursal;
import com.clxsrdev.mercantilAPI.repository.FacturaVehiRepository;
import com.clxsrdev.mercantilAPI.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    SucursalRepository sucursalRepository;

    @Autowired
    private FacturaVehiRepository facturaVehiculoRepository;

    public List<FacturaVehiculo> getFacturasVehiculoBySucursalAndFecha(String nombreSucursal, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturaVehiculoRepository.getFacturasVehiculoBySucursalAndFecha(nombreSucursal, fechaInicio, fechaFin);
    }

    public List<Sucursal> getSucursales(){
        return sucursalRepository.findAll();
    }

    public Sucursal getSucursalNombre(String nombre){
        return sucursalRepository.findByNombreSucursal(nombre);
    }

    public List<Sucursal> getSucursalesActivas() {
        return sucursalRepository.findByEstadoSucursal("Activa");
    }

    public Optional<Sucursal> getSucursal(Long id){
        return sucursalRepository.findById(id);
    }

    public void saveOrUpdate(Sucursal sucursal){
        sucursalRepository.save(sucursal);
    }

    public void delete(Long id){
        sucursalRepository.deleteById(id);
    }
}
