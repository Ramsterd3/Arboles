package main;

import vista.InterfazGrafica;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel del sistema: " + e);
        }

        SwingUtilities.invokeLater(() -> {
            new InterfazGrafica().setVisible(true);
        });
    }
}