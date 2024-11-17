package servicio;

import dao.PedidoDAO;
import modelo.Cliente;
import modelo.Pedido;


import modelo.Producto;

import java.util.List;
public class PedidoServicio {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public Pedido crearPedido(Cliente cliente) {
        Pedido pedido = new Pedido(0, cliente); // ID se generará en la base de datos
        pedidoDAO.insertar(pedido); // Persistir el pedido
        return pedido;
    }
    public List<Producto> obtenerProductosDelPedido(Pedido pedido) {
        return pedidoDAO.obtenerProductosDelPedido(pedido);
    }
}
