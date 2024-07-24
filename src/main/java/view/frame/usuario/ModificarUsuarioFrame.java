package view.frame.usuario;

import model.Usuario;
import database.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ModificarUsuarioFrame extends JFrame {
    private JComboBox<Usuario> usuarioComboBox;
    private JTextField nombreTextField;

    public ModificarUsuarioFrame() throws SQLException {
        setTitle("Modificar Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        java.util.List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
        usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        nombreTextField = new JTextField();

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            Usuario usuarioSeleccionado = (Usuario) usuarioComboBox.getSelectedItem();
            usuarioSeleccionado.setNombre(nombreTextField.getText());
            try {
                usuarioDAO.actualizarUsuario(usuarioSeleccionado);
                JOptionPane.showMessageDialog(this, "Usuario actualizado con éxito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar el usuario");
            }
        });

        setLayout(new GridLayout(3, 2));
        add(new JLabel("Usuario:"));
        add(usuarioComboBox);
        add(new JLabel("Nuevo nombre:"));
        add(nombreTextField);
        add(guardarButton);
    }
}