package view.usuario;

import model.Estudiante;
import model.Profesor;
import model.Usuario;
import service.impl.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AgregarUsuarioFrame extends JFrame {
    private UsuarioDAO usuarioDAO;
    private JTextField nombreUsuarioField;
    private JComboBox<String> rolUsuarioCombo;

    public AgregarUsuarioFrame() {
        try {
            usuarioDAO = new UsuarioDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Agregar Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Nombre Usuario:"));
        nombreUsuarioField = new JTextField();
        panel.add(nombreUsuarioField);

        panel.add(new JLabel("Rol Usuario:"));
        rolUsuarioCombo = new JComboBox<>(new String[]{"Estudiante", "Profesor"});
        panel.add(rolUsuarioCombo);

        JButton agregarUsuarioButton = new JButton("Agregar Usuario");
        agregarUsuarioButton.addActionListener(e -> agregarUsuario());
        panel.add(agregarUsuarioButton);

        add(panel, BorderLayout.CENTER);
    }

    private void agregarUsuario() {
        String nombreUsuario = nombreUsuarioField.getText();
        String rolUsuario = (String) rolUsuarioCombo.getSelectedItem();
        Usuario usuario = "Estudiante".equals(rolUsuario) ? new Estudiante(nombreUsuario) : new Profesor(nombreUsuario);
        try {
            usuarioDAO.agregarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario agregado: " + nombreUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}