package view.frame.libro;

import model.Libro;
import database.dao.LibroDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ConsultarLibrosFrame extends JFrame {

    public ConsultarLibrosFrame() throws SQLException {
        setTitle("Consultar Libros");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros;
        try {
            libros = libroDAO.obtenerTodosLosLibros();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        JList<Libro> librosList = new JList<>(libros.toArray(new Libro[0]));
        JScrollPane scrollPane = new JScrollPane(librosList);
        add(scrollPane, BorderLayout.CENTER);


    }
}
