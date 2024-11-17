package dao;

import modelo.Pedido;
import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.ConexionBD;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public void insertar(Pedido pedido) {
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "INSERT INTO Pedidos (cliente_id, fecha_pedido, total, estado) VALUES (?, NOW(), ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, pedido.getCliente().getId());
            statement.setDouble(2, pedido.getTotal());
            statement.setString(3, "pendiente");  // Asignar un estado por defecto
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));  // Asignar el ID del pedido generado
                }
            } else {
                throw new SQLException("No se pudo insertar el pedido.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void agregarProductoAPedido(Pedido pedido, Producto producto) {
        try (Connection connection = ConexionBD.conectar()) {
            if (pedido.getId() == 0) {
                throw new SQLException("El pedido no tiene un ID válido.");
            }

            String sql = "INSERT INTO Detalles_Pedidos (pedido_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pedido.getId());
            statement.setInt(2, producto.getId());
            statement.setInt(3, 1); // Cantidad fija como ejemplo
            statement.setDouble(4, producto.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar el producto al pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Producto> obtenerProductosDelPedido(Pedido pedido) {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "SELECT p.id, p.nombre, p.precio, dp.cantidad " +
                         "FROM Productos p " +
                         "JOIN Detalles_Pedidos dp ON p.id = dp.producto_id " +
                         "WHERE dp.pedido_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pedido.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Producto producto = new Producto(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getDouble("precio")
                );
                producto.setCantidad(resultSet.getInt("cantidad")); // Asignar cantidad
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
