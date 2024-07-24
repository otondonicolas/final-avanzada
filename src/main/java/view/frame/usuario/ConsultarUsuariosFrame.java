package view.frame.usuario;

import model.Usuario;
import database.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ConsultarUsuariosFrame extends JFrame {
    private JTable usuarioTable;

    public ConsultarUsuariosFrame() throws SQLException {
        setTitle("Consultar Usuarios");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

        String[] columnNames = {"ID", "Nombre"};
        Object[][] data = new Object[usuarios.size()][2];
        for (int i = 0; i < usuarios.size(); i++) {
            data[i][0] = usuarios.get(i).getId();
            data[i][1] = usuarios.get(i).getNombre();
        }

        usuarioTable = new JTable(data, columnNames);
        usuarioTable.setEnabled(false);

        add(new JScrollPane(usuarioTable), BorderLayout.CENTER);
    }
}