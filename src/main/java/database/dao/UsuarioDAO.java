package database.dao;

import model.Usuario;
import model.Estudiante;
import model.Profesor;
import database.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public Usuario agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, rol) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario instanceof Estudiante ? "estudiante" : "profesor");
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int id = generatedKeys.getInt(1);
            usuario = usuario instanceof Estudiante ? new Estudiante(id, usuario.getNombre()) : new Profesor(id, usuario.getNombre());
        }

        return usuario;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, rol = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario instanceof Estudiante ? "estudiante" : "profesor");
        statement.setInt(3, usuario.getId());
        statement.executeUpdate();
    }

    public void eliminarUsuario(Integer usuarioId) throws SQLException {
        // Primero, se eliminan los prestamos del usuario
        String sqlPrestamos = "DELETE FROM prestamos WHERE idUsuario = ?";
        PreparedStatement statementPrestamos = connection.prepareStatement(sqlPrestamos);
        statementPrestamos.setInt(1, usuarioId);
        statementPrestamos.executeUpdate();

        // Después, se elimina el usuario
        String sqlUsuario = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario);
        statementUsuario.setInt(1, usuarioId);
        statementUsuario.executeUpdate();
    }

    public Usuario buscarUsuarioPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE LOWER(nombre) = LOWER(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombre);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String rol = resultSet.getString("rol");
            Usuario usuario;
            if ("estudiante".equals(rol)) {
                usuario = new Estudiante(id, resultSet.getString("nombre"));
            } else {
                usuario = new Profesor(id, resultSet.getString("nombre"));
            }
            return usuario;
        } else {
            return null;
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Usuario> usuarios = new java.util.ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String rol = resultSet.getString("rol");
            Usuario usuario;
            if ("estudiante".equals(rol)) {
                usuario = new Estudiante(id, resultSet.getString("nombre"));
            } else {
                usuario = new Profesor(id, resultSet.getString("nombre"));
            }
            usuarios.add(usuario);
        }

        return usuarios;
    }
}