package io.carpets.DTOs;

import java.util.ArrayList;
import java.util.List;

public class VentaCompletaDTO {
    private int id;
    private String numeroBoleta;
    private double monto;
    private String fecha;
    private String clienteDni;
    private List<DetalleVentaDTO> detalles = new ArrayList<>();

    // Getters y Setters completos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroBoleta() { return numeroBoleta; }
    public void setNumeroBoleta(String numeroBoleta) { this.numeroBoleta = numeroBoleta; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getClienteDni() { return clienteDni; }
    public void setClienteDni(String clienteDni) { this.clienteDni = clienteDni; }

    public List<DetalleVentaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDTO> detalles) { this.detalles = detalles; }
}