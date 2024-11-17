package dao;

import modelo.Producto;
import db.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto El producto a insertar.
     */
    public void insertar(Producto producto) {
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "INSERT INTO Productos (nombre, precio) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                producto.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param producto El producto a actualizar.
     */
    public void actualizar(Producto producto) {
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "UPDATE Productos SET nombre = ?, precio = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param id El ID del producto a eliminar.
     */
    public void eliminar(int id) {
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "DELETE FROM Productos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto.
     * @return El producto correspondiente o null si no se encuentra.
     */
    public Producto obtenerPorId(int id) {
        Producto producto = null;
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "SELECT * FROM Productos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                producto = new Producto(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Una lista de productos.
     */
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = ConexionBD.conectar()) {
            String sql = "SELECT * FROM Productos";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
