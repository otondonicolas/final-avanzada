package service.impl;

import database.DatabaseConnection;
import model.Libro;
import model.LibroDigital;
import model.LibroFisico;
import service.LibroCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements LibroCRUD {
    private Connection connection;

    public LibroDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (titulo, tipo) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, libro.getTitulo());
        statement.setString(2, libro instanceof LibroFisico ? "fisico" : "digital");
        statement.executeUpdate();
    }

    @Override
    public void actualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, tipo = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, libro.getTitulo());
        statement.setString(2, libro instanceof LibroFisico ? "fisico" : "digital");
        statement.setInt(3, libro.getId());
        statement.executeUpdate();
    }

    @Override
    public void eliminarLibro(Libro libro) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM prestamos WHERE idLibro = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setInt(1, libro.getId());
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            throw new SQLException("No se puede eliminar el libro porque tiene préstamos activos");
        }

        // Si llegamos aquí, el libro no tiene préstamos activos y puede ser eliminado
        String query = "DELETE FROM libros WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, libro.getId());
        stmt.executeUpdate();
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        String sql = "SELECT * FROM libros";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Libro> libros = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            String tipo = resultSet.getString("tipo");
            Libro libro;
            if ("fisico".equals(tipo)) {
                libro = new LibroFisico(id, titulo);
            } else {
                libro = new LibroDigital(id, titulo);
            }
            libros.add(libro);
        }
        return libros;
    }
}