package principal;

import modelo.Pedido;
import modelo.Producto;
import servicio.PedidoServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaVerProductosPedido extends JFrame {
    private Pedido pedido;
    private PedidoServicio pedidoServicio;

    public VentanaVerProductosPedido(Pedido pedido) {
        this.pedido = pedido;
        this.pedidoServicio = new PedidoServicio(); // Servicio para obtener productos del pedido

        setTitle("Productos del Pedido");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configurar la tabla
        String[] columnas = {"ID Producto", "Nombre", "Precio Unitario", "Cantidad", "Subtotal"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Obtener productos del pedido
        List<Producto> productos = pedidoServicio.obtenerProductosDelPedido(pedido);
        for (Producto producto : productos) {
            Object[] fila = {
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCantidad(), // Asume que el modelo Producto tiene un campo cantidad
                producto.getPrecio() * producto.getCantidad() // Subtotal
            };
            modeloTabla.addRow(fila);
        }

        // Calcular total del pedido
        double total = productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();

        JLabel lblTotal = new JLabel("Total del Pedido: $" + total);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        setVisible(true);
    }
}
