package controller;

import view.BibliotecaFrame;

import javax.swing.*;

public class BibliotecaController {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BibliotecaFrame().setVisible(true);
        });
    }
}
