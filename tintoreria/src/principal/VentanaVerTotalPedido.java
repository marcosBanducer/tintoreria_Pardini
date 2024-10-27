package principal;

import javax.swing.*;
import modelo.Pedido;
import servicio.ServicioPedido;

public class VentanaVerTotalPedido extends JFrame {
    private ServicioPedido servicioPedido;

    public VentanaVerTotalPedido(Pedido pedido) {
        servicioPedido = new ServicioPedido();
        setTitle("Total del Pedido");
        setSize(300, 150);
        setLocationRelativeTo(null);
        
        double total = servicioPedido.calcularTotal(pedido); // Consulta el total del pedido desde la BD
        
        JLabel lblTotal = new JLabel("Total del pedido: $" + total);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(lblTotal);
        
        setVisible(true);
    }
}
