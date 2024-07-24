package view.frame.libro;

import model.Libro;
import database.dao.LibroDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class EliminarLibroFrame extends JFrame {

    private JComboBox<Libro> libroComboBox;
    private LibroDAO libroDAO;

    public EliminarLibroFrame() throws SQLException {
        libroDAO = new LibroDAO();
        setLayout(new FlowLayout());

        try {
            // Obtener todos los libros de la base de datos
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();

            libroComboBox = new JComboBox<>(libros.toArray(new Libro[0]));
            add(libroComboBox);

            JButton eliminarLibroButton = new JButton("Eliminar libro");
            eliminarLibroButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Libro libroSeleccionado = (Libro) libroComboBox.getSelectedItem();

                        libroDAO.eliminarLibro(libroSeleccionado);

                        libroComboBox.removeItem(libroSeleccionado);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            add(eliminarLibroButton);

            // Configuración del JFrame
            setTitle("Eliminar libro");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}