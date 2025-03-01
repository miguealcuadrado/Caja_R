import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Sistema extends JFrame {

    public Sistema() {
        setSize(700, 600);
        setTitle("CAJA REGISTRADORA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titulo = new JLabel("TIENDA DON CARLITOS.");
        titulo.setBounds(260, 1, 200, 40);
        getContentPane().add(titulo);

        JLabel texto2 = new JLabel("VALOR A PAGAR:");
        texto2.setBounds(35, 55, 180, 40);
        getContentPane().add(texto2);

        JLabel texto3 = new JLabel("VALOR ENTREGADO:");
        texto3.setBounds(180, 55, 180, 40);
        getContentPane().add(texto3);

        JLabel texto4 = new JLabel("SE DEBE DEVOLVER: $0");
        texto4.setBounds(97, 160, 200, 40);
        getContentPane().add(texto4);

        JLabel texto5 = new JLabel("ACTUALIZAR EXISTENCIA:");
        texto5.setBounds(430, 55, 200, 40);
        getContentPane().add(texto5);

        JLabel texto6 = new JLabel("INGRESE LA CANTIDAD DE EXISTENCIA:");
        texto6.setBounds(405, 130, 230, 20);
        getContentPane().add(texto6);

        JTextArea salida = new JTextArea();
        salida.setBounds(35, 250, 260, 184);
        salida.setEditable(false);
        JScrollPane scrollSalida = new JScrollPane(salida);
        scrollSalida.setBounds(35, 250, 260, 184);
        getContentPane().add(scrollSalida);

        JTextField valorPagar = new JTextField();
        valorPagar.setBounds(30, 90, 105, 20);
        getContentPane().add(valorPagar);

        JTextField valorEntregado = new JTextField();
        valorEntregado.setBounds(185, 90, 105, 20);
        getContentPane().add(valorEntregado);

        JTextField cantidadExistencias = new JTextField();
        cantidadExistencias.setBounds(450, 163, 105, 20);
        getContentPane().add(cantidadExistencias);

        JButton calcular = new JButton("DEVOLVER");
        calcular.setBounds(105, 130, 110, 20);
        getContentPane().add(calcular);

        JButton insertar = new JButton("INSERTAR");
        insertar.setBounds(447, 200, 110, 20);
        getContentPane().add(insertar);

        JButton limpiar = new JButton("LIMPIAR");
        limpiar.setBounds(285, 460, 110, 20);
        getContentPane().add(limpiar);

        JComboBox<Integer> existencia = new JComboBox<>();
        existencia.setBounds(440, 90, 120, 20);
        Integer[] opciones = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100};
        DefaultComboBoxModel<Integer> modeloOpciones = new DefaultComboBoxModel<>(opciones);
        existencia.setModel(modeloOpciones);
        getContentPane().add(existencia);

        String[] columnas = {"DENOMINACIÓN", "EXISTENCIA", "DEVUELTA"};
        Object[][] filas = {
                {100000, 5, 0},
                {50000, 5, 0},
                {20000, 5, 0},
                {10000, 5, 0},
                {5000, 10, 0},
                {2000, 10, 0},
                {1000, 10, 0},
                {500, 15, 0},
                {200, 15, 0},
                {100, 15, 0}
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(filas, columnas);
        JTable tabla = new JTable(modeloTabla);
        JScrollPane disTabla = new JScrollPane(tabla);
        disTabla.setBounds(380, 250, 260, 184);
        getContentPane().add(disTabla);

        
        DocumentListener soloNumeros = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validarNumeros(); }
            public void removeUpdate(DocumentEvent e) { validarNumeros(); }
            public void changedUpdate(DocumentEvent e) { validarNumeros(); }

            private void validarNumeros() {
                if (!valorPagar.getText().matches("\\d*")) valorPagar.setText(valorPagar.getText().replaceAll("\\D", ""));
                if (!valorEntregado.getText().matches("\\d*")) valorEntregado.setText(valorEntregado.getText().replaceAll("\\D", ""));
                if (!cantidadExistencias.getText().matches("\\d*")) cantidadExistencias.setText(cantidadExistencias.getText().replaceAll("\\D", ""));
            }
        };

        valorPagar.getDocument().addDocumentListener(soloNumeros);
        valorEntregado.getDocument().addDocumentListener(soloNumeros);
        cantidadExistencias.getDocument().addDocumentListener(soloNumeros);

        
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valorPagar.setText("");
                valorEntregado.setText("");
                salida.setText("");
                texto4.setText("SE DEBE DEVOLVER: $0");
            }
        });

        
        calcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int pagar = Integer.parseInt(valorPagar.getText());
                    int entregado = Integer.parseInt(valorEntregado.getText());
                    if (entregado < pagar) {
                        JOptionPane.showMessageDialog(null, "El valor entregado no es suficiente.");
                        return;
                    }
                    int cambio = entregado - pagar;
                    texto4.setText("SE DEBE DEVOLVER: $" + cambio);
                    salida.setText("Cambio a entregar:\n");
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        int denominacion = (int) modeloTabla.getValueAt(i, 0);
                        int cantidadDisponible = (int) modeloTabla.getValueAt(i, 1);
                        int cantidadUsada = Math.min(cambio / denominacion, cantidadDisponible);
                        cambio -= cantidadUsada * denominacion;
                        modeloTabla.setValueAt(cantidadUsada, i, 2);
                        modeloTabla.setValueAt(cantidadDisponible - cantidadUsada, i, 1);
                        if (cantidadUsada > 0) {
                            salida.append(cantidadUsada + " x $" + denominacion + "\n");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores válidos.");
                }
            }
        });

        
        insertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int cantidad = Integer.parseInt(cantidadExistencias.getText());
                    int seleccionado = existencia.getSelectedIndex();
                    int actual = (int) modeloTabla.getValueAt(seleccionado, 1);
                    modeloTabla.setValueAt(actual + cantidad, seleccionado, 1);
                    cantidadExistencias.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new Sistema().setVisible(true);
    }
}
