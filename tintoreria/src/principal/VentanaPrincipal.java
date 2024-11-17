package principal;

import java.awt.GridLayout;
import javax.swing.*;
import modelo.Cliente;
import modelo.Pedido;
import servicio.PedidoServicio;

public class VentanaPrincipal extends JFrame {
    private Pedido pedido;
    private PedidoServicio pedidoServicio = new PedidoServicio(); // Servicio que maneja la lógica de negocio

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Pedidos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un pedido y persistir en la base de datos
        Cliente cliente = new Cliente("Juan Perez", "juan@example.com", "123456789");
        pedido = pedidoServicio.crearPedido(cliente);

        // Crear el panel de botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAgregarProducto = new JButton("Agregar Producto al Pedido");
        JButton btnVerTotalPedido = new JButton("Ver Total del Pedido");
        btnVerTotalPedido.addActionListener(e -> new VentanaVerProductosPedido(pedido));

        JButton btnSalir = new JButton("Salir");

        panel.add(btnAgregarProducto);
        panel.add(btnVerTotalPedido);
        panel.add(btnSalir);

        add(panel);

        // Acciones de los botones
        btnAgregarProducto.addActionListener(e -> new VentanaAgregarProducto(pedido));
        btnVerTotalPedido.addActionListener(e -> new VentanaVerTotalPedido(pedido));
        btnSalir.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
