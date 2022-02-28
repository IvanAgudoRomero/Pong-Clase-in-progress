package Vista;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Ranking extends JFrame {
    private JTextArea textArea;
    private int difGoles;
    private String ganador;
    private String perdedor;
    private Comparador comparador;

    public Ranking(int difGoles, String ganador, String perdedor){
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.difGoles = difGoles;
        textArea = new JTextArea();
        textArea.setEditable(false);
        setLayout(null);

        add(textArea);

        textArea.setBounds(100,50,570,470);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("rankingpkg/Ranking");
        setSize(800, 600);
        setVisible(true);
        persisteDato();
    }

    public void persisteDato(){
        File f = new File("src/main/java/rankingpkg/Ranking");
        BufferedReader br = null;
        BufferedWriter bw = null;
        String registro;
        String registroAux;
        String[] datosRegistro;
        String[] datosRegistroAux;
        Date fecha = new Date();
        String[] lineas = new String[11];

        registro = ganador+"#"+perdedor+"#"+difGoles+"#"+fecha;

        datosRegistro = registro.split("#");

        try {
            br = new BufferedReader(new FileReader(f));
            bw = new BufferedWriter(new FileWriter(f));

            lineas[0]=registro;
            for (int j = 1; j < 11; j++) {
                if(lineas[j]==null){
                    break;
                }
                lineas[j]=br.readLine();
            }


            //Arrays.sort(lineas, comparador);

            for (int j = 0; j < 10; j++) {
                if(lineas[j]==null){
                    break;
                }
                textArea.setText(textArea.getText()+lineas[j]);
            }

            //f.delete();
            for (int j = 0; j < 10; j++) {
                bw.write(lineas[j]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Ranking r = new Ranking(0, "ejemplo1", "ejemplo2");
    }
}
