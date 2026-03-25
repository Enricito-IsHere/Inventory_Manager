package io.carpets.repositories;

import io.carpets.entidades.Venta;
import java.util.List;
import io.carpets.DTOs.VentaCompletaDTO;
import io.carpets.DTOs.DetalleVentaDTO;


public interface VentaRepository {
    boolean save(Venta venta);
    boolean update(Venta venta);
    boolean delete(int id);
    Venta findById(int id);
    List<Venta> findAll();
    List<Venta> findByNumeroBoleta(String numeroBoleta);
    List<VentaCompletaDTO> listarVentasConDetalles();



    //  registrar productos no encontrados
    void registrarProductoNoEncontrado(Integer idProductoSolicitado, String nombreProductoSolicitado, Integer vendedorId);
}