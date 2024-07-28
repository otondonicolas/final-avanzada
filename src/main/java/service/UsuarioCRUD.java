package service;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioCRUD {
    void agregarUsuario(Usuario usuario) throws SQLException;

    void actualizarUsuario(Usuario usuario) throws SQLException;

    void eliminarUsuario(Integer id) throws SQLException;
    List<Usuario> obtenerTodosLosUsuarios() throws SQLException;
}
