package servicio;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;

import db.ConexionBD;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioPedido {
    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(int id, Cliente cliente) {
        Pedido nuevoPedido = new Pedido(id, cliente);
        pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

   



    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }
    public void agregarProductoAPedido(Pedido pedido, Producto producto) {
        Connection conexion = null;
        try {
            conexion = ConexionBD.conectar();
            String sql = "INSERT INTO detallepedido (idDetallePedido, idPedido,idPrenda,idServicio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, pedido.getId());
            statement.setInt(2, producto.getId());
            statement.setInt(3, 1); // Ejemplo de cantidad
            statement.setDouble(4, producto.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
    }

    public double calcularTotal(Pedido pedido) {
        Connection conexion = null;
        double total = 0.0;
        try {
            conexion = ConexionBD.conectar();
            String sql = "SELECT SUM(precio_unitario * cantidad) AS total FROM Detalles_Pedidos WHERE pedido_id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, pedido.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
        return total;
    }
}