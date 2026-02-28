package io.carpets.repositories;

import io.carpets.entidades.Producto;
import java.util.List;
import java.util.Map;

public interface ProductoRepository {
    boolean save(Producto producto);
    boolean update(Producto producto);
    boolean delete(int id);
    Producto findById(int id);
    List<Map<String, Object>> findAll();
    List<Map<String, Object>> findByCategoria(String categoriaNombre);
    List<Map<String, Object>> findByNombre(String nombre);
    double getGananciaTotal();

    boolean existeIdById(int id);

}








