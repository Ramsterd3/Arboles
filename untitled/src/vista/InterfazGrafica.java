package vista;

import modelo.ArbolBinario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class InterfazGrafica extends JFrame {
    private ArbolBinario arbol = new ArbolBinario();
    private JTextField campoEntrada;
    private JTextArea areaResultados;

    public InterfazGrafica() {
        setTitle("Operaciones de Árbol Binario (Sin Gráfico)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        panelControles.add(new JLabel("Valor:"));
        campoEntrada = new JTextField(6);
        panelControles.add(campoEntrada);

        JButton btnInsertar = new JButton("Insertar");
        panelControles.add(btnInsertar);

        JButton btnBuscar = new JButton("Buscar");
        panelControles.add(btnBuscar);

        JButton btnEliminar = new JButton("Eliminar");
        panelControles.add(btnEliminar);

        JButton btnInorden = new JButton("Inorden");
        panelControles.add(btnInorden);

        JButton btnPreorden = new JButton("Preorden");
        panelControles.add(btnPreorden);

        JButton btnPostorden = new JButton("Postorden");
        panelControles.add(btnPostorden);

        add(panelControles, BorderLayout.NORTH);

        areaResultados = new JTextArea(10, 40); // Un poco más grande ahora
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPaneResultados = new JScrollPane(areaResultados);
        add(scrollPaneResultados, BorderLayout.CENTER); // JTextArea en el centro

        // ActionListeners
        btnInsertar.addActionListener(this::accionInsertar);
        btnBuscar.addActionListener(this::accionBuscar);
        btnEliminar.addActionListener(this::accionEliminar);
        btnInorden.addActionListener(this::accionInorden);
        btnPreorden.addActionListener(this::accionPreorden);
        btnPostorden.addActionListener(this::accionPostorden);

        pack();
        setMinimumSize(new Dimension(500, 200)); // Ajustar el mínimo
        setLocationRelativeTo(null);
    }

    private void limpiarCampoYFoco() {
        campoEntrada.setText("");
        campoEntrada.requestFocusInWindow();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        limpiarCampoYFoco();
    }

    private int obtenerValorEntrada() throws NumberFormatException,IllegalArgumentException {
        String texto = campoEntrada.getText().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo está vacío.");
        }
        return Integer.parseInt(texto);
    }

    private void accionInsertar(ActionEvent e) {
        try {
            int dato = obtenerValorEntrada();
            arbol.insertar(dato);
            areaResultados.append("Insertado: " + dato + "\n");
            limpiarCampoYFoco();
        } catch (NumberFormatException ex) {
            mostrarError("Entrada no válida (número).");
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void accionBuscar(ActionEvent e) {
        try {
            int dato = obtenerValorEntrada();
            boolean encontrado = arbol.buscar(dato);
            areaResultados.append(dato + (encontrado ? " encontrado." : " no encontrado.") + "\n");
            limpiarCampoYFoco();
        } catch (NumberFormatException ex) {
            mostrarError("Entrada no válida (número).");
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void accionEliminar(ActionEvent e) {
        try {
            int dato = obtenerValorEntrada();
            boolean existeAntes = arbol.buscar(dato);
            arbol.eliminar(dato);
            areaResultados.append("Eliminado: " + dato + (existeAntes ? "" : " (no existía)") + "\n");
            // Ya no hay panelArbol.repaint();
            limpiarCampoYFoco();
        } catch (NumberFormatException ex) {
            mostrarError("Entrada no válida (número).");
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private String formatearLista(List<Integer> lista) {
        if (lista == null || lista.isEmpty()) {
            return "Vacío";
        }
        return lista.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    private void accionInorden(ActionEvent e) {
        areaResultados.append("Inorden: " + formatearLista(arbol.recorridoInorden()) + "\n");
    }

    private void accionPreorden(ActionEvent e) {
        areaResultados.append("Preorden: " + formatearLista(arbol.recorridoPreorden()) + "\n");
    }

    private void accionPostorden(ActionEvent e) {
        areaResultados.append("Postorden: " + formatearLista(arbol.recorridoPostorden()) + "\n");
    }

}
