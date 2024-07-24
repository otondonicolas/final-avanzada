package view.frame.libro;

import model.Libro;
import database.dao.LibroDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ModificarLibroFrame extends JFrame {
    private JComboBox<Libro> libroComboBox;
    private JTextField nombreTextField;

    public ModificarLibroFrame() throws SQLException {
        setTitle("Modificar Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        LibroDAO libroDAO = new LibroDAO();
        java.util.List<Libro> libros = libroDAO.obtenerTodosLosLibros();
        libroComboBox = new JComboBox<>(libros.toArray(new Libro[0]));
        nombreTextField = new JTextField();

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            Libro libroSeleccionado = (Libro) libroComboBox.getSelectedItem();
            if (libroSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "No hay libros");
                return;
            }
            libroSeleccionado.setTitulo(nombreTextField.getText());
            try {
                libroDAO.actualizarLibro(libroSeleccionado);
                JOptionPane.showMessageDialog(this, "Libro actualizado con éxito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar el libro");
            }
        });

        setLayout(new GridLayout(3, 2));
        add(new JLabel("Libro:"));
        add(libroComboBox);
        add(new JLabel("Nuevo nombre:"));
        add(nombreTextField);
        add(guardarButton);
    }
}