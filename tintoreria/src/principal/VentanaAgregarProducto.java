package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Producto;
import modelo.Pedido;
import servicio.ServicioPedido;

public class VentanaAgregarProducto extends JFrame {
    private JTextField txtProducto;
    private JTextField txtPrecio;
    private ServicioPedido servicioPedido = new ServicioPedido();
    private Pedido pedido; // Agrega el pedido al constructor

    public VentanaAgregarProducto(Pedido pedido) {
        this.pedido = pedido;
        setTitle("Agregar Producto");
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel lblProducto = new JLabel("Nombre del Producto:");
        txtProducto = new JTextField();
        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();
        
        JButton btnAgregar = new JButton("Agregar");
        
        panel.add(lblProducto);
        panel.add(txtProducto);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAgregar);
        
        add(panel);
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtProducto.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                Producto producto = new Producto(1, nombre, precio);
                servicioPedido.agregarProductoAPedido(pedido, producto); // Añadir a pedido en BD
                JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
                dispose(); // Cierra la ventana
            }
        });
        
        setVisible(true);
    }
}
