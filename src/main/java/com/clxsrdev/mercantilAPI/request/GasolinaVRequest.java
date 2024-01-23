package com.clxsrdev.mercantilAPI.request;

import lombok.Getter;

import java.util.Date;

public class GasolinaVRequest {
    private Long idVehiculo;

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Double getLtsGas() {
        return ltsGas;
    }

    public void setLtsGas(Double ltsGas) {
        this.ltsGas = ltsGas;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    private String ruta;
    private Double ltsGas;
    private Date fechaCarga;
    private Integer kilometraje;

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    private Integer monto;
}
