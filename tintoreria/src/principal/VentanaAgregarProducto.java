package principal;

import modelo.Pedido;
import modelo.Producto;
import servicio.ProductoServicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaAgregarProducto extends JFrame {
    private Pedido pedido;
    private ProductoServicio productoServicio = new ProductoServicio();

    public VentanaAgregarProducto(Pedido pedido) {
        this.pedido = pedido;

        setTitle("Agregar Producto");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre del Producto:");
        JTextField txtNombre = new JTextField();
        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();
        JButton btnAgregar = new JButton("Agregar");

        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(new JLabel());
        panel.add(btnAgregar);

        add(panel);

        btnAgregar.addActionListener((ActionEvent e) -> {
            try {
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                Producto producto = new Producto(0, nombre, precio);
                productoServicio.agregarProductoAPedido(pedido, producto);
                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
