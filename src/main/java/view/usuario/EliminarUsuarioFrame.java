package view.usuario;

import model.Usuario;
import service.impl.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EliminarUsuarioFrame extends JFrame {
    private JComboBox<Usuario> usuarioComboBox;

    public EliminarUsuarioFrame() throws SQLException {
        setTitle("Eliminar Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        java.util.List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
        usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> {
            Usuario usuarioSeleccionado = (Usuario) usuarioComboBox.getSelectedItem();
            try {
                if (usuarioSeleccionado == null) {
                    JOptionPane.showMessageDialog(this, "Usuario no seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                usuarioDAO.eliminarUsuario(usuarioSeleccionado.getId());
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario");
            }
        });

        setLayout(new GridLayout(2, 2));
        add(new JLabel("Usuario:"));
        add(usuarioComboBox);
        add(eliminarButton);
    }

}
