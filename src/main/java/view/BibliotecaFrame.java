package view;

import view.frame.libro.*;
import view.frame.usuario.*;
import view.libro.*;
import view.usuario.AgregarUsuarioFrame;
import view.usuario.ConsultarUsuariosFrame;
import view.usuario.EliminarUsuarioFrame;
import view.usuario.ModificarUsuarioFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BibliotecaFrame extends JFrame {
    public BibliotecaFrame() {
        setTitle("Biblioteca");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton agregarLibroButton = new JButton("Agregar Libro");
        agregarLibroButton.addActionListener(e -> new AgregarLibroFrame().setVisible(true));

        JButton eliminarLibroButton = new JButton("Eliminar Libro");
        eliminarLibroButton.addActionListener(e -> {
            try {
                new EliminarLibroFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton prestarLibroButton = new JButton("Prestar Libro");
        prestarLibroButton.addActionListener(e -> new PrestarLibroFrame().setVisible(true));

        JButton eliminarPrestamoButton = new JButton("Eliminar Prestamo");
        eliminarPrestamoButton.addActionListener(e -> new EliminarPrestamoFrame().setVisible(true));


        JButton modificarLibroButton = new JButton("Modificar Libro");
        modificarLibroButton.addActionListener(e -> {
            try {
                new ModificarLibroFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


        JButton consultarLibrosButton = new JButton("Consultar Libros");
        consultarLibrosButton.addActionListener(e -> {
            try {
                new ConsultarLibrosFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


        JButton agregarUsuarioButton = new JButton("Agregar Usuario");
        agregarUsuarioButton.addActionListener(e -> new AgregarUsuarioFrame().setVisible(true));

        JButton modificarUsuarioButton = new JButton("Modificar Usuario");
        modificarUsuarioButton.addActionListener(e -> {
            try {
                new ModificarUsuarioFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton eliminarUsuarioButton = new JButton("Eliminar Usuario");
        eliminarUsuarioButton.addActionListener(e -> {
            try {
                new EliminarUsuarioFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton consultarUsuariosButton = new JButton("Consultar Usuarios");
        consultarUsuariosButton.addActionListener(e -> {
            try {
                new ConsultarUsuariosFrame().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


        panel.add(agregarLibroButton);
        panel.add(eliminarLibroButton);
        panel.add(prestarLibroButton);
        panel.add(eliminarPrestamoButton);
        panel.add(modificarLibroButton);
        panel.add(consultarLibrosButton);
        panel.add(agregarUsuarioButton);
        panel.add(eliminarUsuarioButton);
        panel.add(modificarUsuarioButton);

        add(panel, BorderLayout.CENTER);
    }
}
