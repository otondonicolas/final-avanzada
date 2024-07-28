package view.libro;

import model.Prestamo;
import service.impl.PrestamoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EliminarPrestamoFrame extends JFrame {
    private PrestamoDAO prestamoDAO;
    private JComboBox<Prestamo> prestamoComboBox;

    public EliminarPrestamoFrame() {
        try {
            prestamoDAO = new PrestamoDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Eliminar Prestamo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerTodosLosPrestamos();
            prestamoComboBox = new JComboBox<>(prestamos.toArray(new Prestamo[0]));
            panel.add(new JLabel("Prestamo:"));
            panel.add(prestamoComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton eliminarPrestamoButton = new JButton("Eliminar Prestamo");
        eliminarPrestamoButton.addActionListener(e -> eliminarPrestamo());
        panel.add(eliminarPrestamoButton);

        add(panel, BorderLayout.CENTER);
    }

    private void eliminarPrestamo() {
        Prestamo prestamo = (Prestamo) prestamoComboBox.getSelectedItem();
        try {
            if (prestamo == null) {
                JOptionPane.showMessageDialog(this, "Prestamo no seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            prestamoDAO.eliminarPrestamo(prestamo.getId());
            JOptionPane.showMessageDialog(this, "Prestamo eliminado con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}