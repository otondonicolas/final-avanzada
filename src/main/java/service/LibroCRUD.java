package service;

import model.Libro;

import java.sql.SQLException;
import java.util.List;

public interface LibroCRUD {
    void agregarLibro(Libro libro) throws SQLException;

    void actualizarLibro(Libro libro) throws SQLException;

    void eliminarLibro(Libro libro) throws SQLException;

    List<Libro> obtenerTodosLosLibros() throws SQLException;
}
