package service;

import model.Libro;
import model.Prestamo;
import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface PrestamoCRUD {
    void agregarPrestamo(Usuario usuario, Libro libro) throws SQLException;

    void eliminarPrestamo(Integer prestamoId) throws SQLException;

    List<Prestamo> obtenerTodosLosPrestamos() throws SQLException;
}
