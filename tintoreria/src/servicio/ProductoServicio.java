package servicio;

import dao.ProductoDAO;
import dao.PedidoDAO;
import modelo.Pedido;
import modelo.Producto;

public class ProductoServicio {
    private ProductoDAO productoDAO = new ProductoDAO(); // DAO para productos
    private PedidoDAO pedidoDAO = new PedidoDAO(); // DAO para pedidos

    /**
     * Agrega un producto al pedido y actualiza la base de datos.
     *
     * @param pedido   El pedido al que se agregará el producto.
     * @param producto El producto que se agregará al pedido.
     */
    public void agregarProductoAPedido(Pedido pedido, Producto producto) {
        // Persistir el producto en la base de datos
        productoDAO.insertar(producto);

        // Agregar el producto al pedido en la base de datos
        pedidoDAO.agregarProductoAPedido(pedido, producto);
    }
}
