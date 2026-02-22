package io.carpets.repositories;

import io.carpets.entidades.Venta;
import java.util.List;

public interface VentaRepository {
    boolean save(Venta venta);
    boolean update(Venta venta);
    boolean delete(int id);
    Venta findById(int id);
    List<Venta> findAll();
    List<Venta> findByNumeroBoleta(String numeroBoleta);

    //  registrar productos no encontrados
    void registrarProductoNoEncontrado(Integer idProductoSolicitado, String nombreProductoSolicitado, Integer vendedorId);
}