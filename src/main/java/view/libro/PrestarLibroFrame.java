package view.libro;

import model.Libro;
import model.Usuario;
import service.impl.LibroDAO;
import service.impl.PrestamoDAO;
import service.impl.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PrestarLibroFrame extends JFrame {
    private UsuarioDAO usuarioDAO;
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private JComboBox<Usuario> usuarioComboBox;
    private JComboBox<Libro> libroComboBox;

    public PrestarLibroFrame() {
        try {
            usuarioDAO = new UsuarioDAO();
            libroDAO = new LibroDAO();
            prestamoDAO = new PrestamoDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Prestar Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));
            panel.add(new JLabel("Usuario:"));
            panel.add(usuarioComboBox);

            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            libroComboBox = new JComboBox<>(libros.toArray(new Libro[0]));
            panel.add(new JLabel("Libro:"));
            panel.add(libroComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton prestarLibroButton = new JButton("Prestar Libro");
        prestarLibroButton.addActionListener(e -> prestarLibro());
        panel.add(prestarLibroButton);

        add(panel, BorderLayout.CENTER);
    }

    private void prestarLibro() {
        Usuario usuario = (Usuario) usuarioComboBox.getSelectedItem();
        Libro libro = (Libro) libroComboBox.getSelectedItem();
        try {
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Usuario no seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (libro == null) {
                JOptionPane.showMessageDialog(this, "Libro no seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            prestamoDAO.agregarPrestamo(usuario, libro);
            JOptionPane.showMessageDialog(this, "Libro prestado al usuario " + usuario.getNombre() + ": " + libro.getTitulo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}