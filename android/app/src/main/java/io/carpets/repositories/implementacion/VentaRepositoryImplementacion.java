package io.carpets.repositories.implementacion;

import io.carpets.Configuracion.ConfiguracionBaseDatos;
import io.carpets.entidades.Venta;
import io.carpets.repositories.VentaRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaRepositoryImplementacion implements VentaRepository {

    @Override
    public boolean save(Venta venta) {
        String sql = "INSERT INTO venta (numero_boleta, fecha, monto, descripcion, vendedor_idvendedor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, venta.getNumeroBoleta());
            // 🟢 Timestamp aplicado para guardar la hora exacta
            stmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setDouble(3, venta.getMonto());
            stmt.setString(4, venta.getDescripcion());
            stmt.setInt(5, venta.getVendedorId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    venta.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Venta venta) {
        String sql = "UPDATE venta SET numero_boleta=?, fecha=?, monto=?, descripcion=?, vendedor_idvendedor=? WHERE idventa=?";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venta.getNumeroBoleta());
            // 🟢 Timestamp aplicado
            stmt.setTimestamp(2, new java.sql.Timestamp(venta.getFecha() != null ? venta.getFecha().getTime() : System.currentTimeMillis()));
            stmt.setDouble(3, venta.getMonto());
            stmt.setString(4, venta.getDescripcion());
            stmt.setInt(5, venta.getVendedorId());
            stmt.setInt(6, venta.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM venta WHERE idventa=?";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Venta findById(int id) {
        String sql = "SELECT * FROM venta WHERE idventa=?";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("idventa"));
                // 🟢 getTimestamp aplicado
                v.setFecha(rs.getTimestamp("fecha"));
                v.setMonto(rs.getDouble("monto"));
                v.setDescripcion(rs.getString("descripcion"));
                // 🟢 Extraemos numero_boleta
                v.setNumeroBoleta(rs.getString("numero_boleta"));
                v.setVendedorId(rs.getInt("vendedor_idvendedor"));
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Venta> findAll() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("idventa"));
                // 🟢 getTimestamp aplicado
                v.setFecha(rs.getTimestamp("fecha"));
                v.setMonto(rs.getDouble("monto"));
                v.setDescripcion(rs.getString("descripcion"));
                // 🟢 Extraemos numero_boleta
                v.setNumeroBoleta(rs.getString("numero_boleta"));
                v.setVendedorId(rs.getInt("vendedor_idvendedor"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Venta> findByNumeroBoleta(String numeroBoleta) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta WHERE numero_boleta=?";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroBoleta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("idventa"));
                v.setFecha(rs.getTimestamp("fecha"));
                v.setMonto(rs.getDouble("monto"));
                v.setDescripcion(rs.getString("descripcion"));
                v.setNumeroBoleta(rs.getString("numero_boleta"));
                v.setVendedorId(rs.getInt("vendedor_idvendedor"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void registrarProductoNoEncontrado(Integer idProductoSolicitado, String nombreProductoSolicitado, Integer vendedorId) {
        String sql = "INSERT INTO log_producto_no_encontrado (id_producto_solicitado, nombre_producto_solicitado, fecha_solicitud, vendedor_id) VALUES (?, ?, NOW(), ?)";
        try (Connection conn = ConfiguracionBaseDatos.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (idProductoSolicitado != null) {
                stmt.setInt(1, idProductoSolicitado);
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            stmt.setString(2, nombreProductoSolicitado);
            if (vendedorId != null) {
                stmt.setInt(3, vendedorId);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}