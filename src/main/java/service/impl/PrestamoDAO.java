package service.impl;

import model.Libro;
import model.Prestamo;
import model.Usuario;
import database.DatabaseConnection;
import service.PrestamoCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO implements PrestamoCRUD {
    private Connection connection;

    public PrestamoDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void agregarPrestamo(Usuario usuario, Libro libro) throws SQLException {
        String sql = "INSERT INTO prestamos (idUsuario, idLibro) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.setInt(2, libro.getId());
        statement.executeUpdate();
    }

    @Override
    public void eliminarPrestamo(Integer prestamoId) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, prestamoId);
        statement.executeUpdate();
    }

    @Override
    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT p.id, u.nombre AS usuarioNombre, l.titulo AS libroTitulo FROM prestamos p " +
                "JOIN usuarios u ON p.idUsuario = u.id " +
                "JOIN libros l ON p.idLibro = l.id";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String usuarioNombre = resultSet.getString("usuarioNombre");
            String libroTitulo = resultSet.getString("libroTitulo");
            Prestamo prestamo = new Prestamo();
            prestamo.setId(id);
            prestamo.setUsuarioNombre(usuarioNombre);
            prestamo.setLibroTitulo(libroTitulo);
            prestamos.add(prestamo);
        }
        return prestamos;
    }
}