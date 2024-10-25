package servicio;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ServicioPedido {
    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(int id, Cliente cliente) {
        Pedido nuevoPedido = new Pedido(id, cliente);
        pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public void agregarProductoAPedido(Pedido pedido, Producto producto) {
        pedido.agregarProducto(producto);
    }

    public double calcularTotal(Pedido pedido) {
        return pedido.getTotal();
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }
}
