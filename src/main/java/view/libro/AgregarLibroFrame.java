package view.libro;

import model.Libro;
import model.LibroDigital;
import model.LibroFisico;
import service.impl.LibroDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AgregarLibroFrame extends JFrame {
    private LibroDAO libroDAO;
    private JTextField tituloLibroField;
    private JComboBox<String> tipoLibroCombo;

    public AgregarLibroFrame() {
        try {
            libroDAO = new LibroDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Agregar Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Título Libro:"));
        tituloLibroField = new JTextField();
        panel.add(tituloLibroField);

        panel.add(new JLabel("Tipo Libro:"));
        tipoLibroCombo = new JComboBox<>(new String[]{"Físico", "Digital"});
        panel.add(tipoLibroCombo);

        JButton agregarLibroButton = new JButton("Agregar Libro");
        agregarLibroButton.addActionListener(e -> agregarLibro());
        panel.add(agregarLibroButton);

        add(panel, BorderLayout.CENTER);
    }

    private void agregarLibro() {
        String tituloLibro = tituloLibroField.getText();
        String tipoLibro = (String) tipoLibroCombo.getSelectedItem();
        Libro libro = "Físico".equals(tipoLibro) ? new LibroFisico(tituloLibro) : new LibroDigital(tituloLibro);
        try {
            libroDAO.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro agregado: " + tituloLibro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
