package io.carpets.servicios;

import io.carpets.entidades.Producto;
import java.util.List;
import java.util.Map;

public interface ServicioProducto {


    boolean validarStock(int productoId, int cantidad);
    void actualizarInventario(Producto producto);
    List<Map<String, Object>> obtenerTodos();
    Producto obtenerPorId(int id);
    List<Map<String, Object>> buscarProductos(String criterio, String tipo);

    double getGananciaTotal();

    boolean agregarProducto(Producto producto);

    boolean eliminarProducto(int idProducto);
}