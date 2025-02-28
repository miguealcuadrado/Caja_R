import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextArea;



public class Sistema extends JFrame {

    

    

    public Sistema () {
        setSize(700,600);
        setTitle("CAJA REGISTRADORA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel Titulo = new JLabel("TIENDA DON CARLITOS.");
        Titulo.setBounds(260,1,200,40);
        getContentPane().add(Titulo);

        JLabel Texto_2 = new JLabel("VALOR A PAGAR:");
        Texto_2.setBounds(35,55,180,40);
        getContentPane().add(Texto_2);

        JLabel Texto_3 = new JLabel("VALOR ENTREGADO:");
        Texto_3.setBounds(180,55, 180, 40);
        getContentPane().add(Texto_3);

        int Devuelta = 0;
        JLabel Texto_4 = new JLabel("SE DEBE DEVOLVER: " + Devuelta + "$");
        Texto_4.setBounds(97, 160, 180, 40);
        getContentPane().add(Texto_4);

        JLabel Texto_5 = new JLabel("ACTUALIZAR EXISTENCIA:");
        Texto_5.setBounds(430, 55, 200, 40);
        getContentPane().add(Texto_5);
        
        JLabel Texto_6 = new JLabel("INGRESE LA CANTIDAD DE EXISTENCIA:");
        Texto_6.setBounds(405, 130, 230, 20);
        getContentPane().add(Texto_6);

        JTextArea Salida = new JTextArea();
        Salida.setBounds(35, 250, 260, 184);
        Salida.setEditable(false);

        JScrollPane Scroll_Salida = new JScrollPane(Salida);
        Scroll_Salida.setBounds(35,250,260,184);
        getContentPane().add(Scroll_Salida);




        

        JTextField Valor_A_Pagar = new JTextField();
        Valor_A_Pagar.setBounds(30, 90, 105, 20);
        getContentPane().add(Valor_A_Pagar);

        JTextField Valor_Entregado = new JTextField();
        Valor_Entregado.setBounds(185, 90, 105, 20);
        getContentPane().add(Valor_Entregado);
        
        JTextField Cantidad_Existencias = new JTextField();
        Cantidad_Existencias.setBounds(450, 163, 105,20);
        getContentPane().add(Cantidad_Existencias);


        
        
        JButton Calcular = new JButton("DEVOLVER");
        Calcular.setBounds(105, 130, 110, 20);
        getContentPane().add(Calcular);

        JButton Visualizar = new JButton("VISUALIZAR");
        Visualizar.setBounds(105, 200,110,20);
        getContentPane().add(Visualizar);
       
        JButton Insertar = new JButton("INSERTAR");
        Insertar.setBounds(447, 200, 110,20);
        getContentPane().add(Insertar);

        JButton Actualizar = new JButton("ACTUALIZAR");
        Actualizar.setBounds(285,460, 110, 20);
        getContentPane().add(Actualizar);




        JComboBox<Integer> Existencia = new JComboBox<>();  
        Existencia.setBounds(440, 90, 120, 20);

        Integer[] opciones = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100};
        DefaultComboBoxModel<Integer> Modelo_Opciones = new DefaultComboBoxModel<>(opciones);
        Existencia.setModel(Modelo_Opciones);
        getContentPane().add(Existencia);

        String[] Columnas = {"DENOMINACIÓN" , "EXISTENCIA" , "DEVUELTA"};

        Object[][] filas = { 
            {100000, 5, 0} ,
            {50000, 5, 0} ,
            {20000, 5, 0} ,
            {10000, 5, 0} ,
            {5000, 10, 0},
            {2000, 10, 0} ,
            {1000, 10, 0} ,
            {500, 15, 0} ,
            {200, 15, 0} ,
            {100, 15, 0}   
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(filas, Columnas);
        
        JTable Tabla = new JTable(modeloTabla);
        JScrollPane Dis_Tabla = new JScrollPane(Tabla);
        Dis_Tabla.setBounds(380, 250, 260, 184);
        getContentPane().add(Dis_Tabla);

        




       

        









        










    }
}
