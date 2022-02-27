package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vista extends JFrame {

    protected JLabel plataformaI, plataformaD, bola;
    protected JLabel puntosI, puntosD;
    protected JLabel numeroI, numeroD;
    private JPanel escenario;
    private JPanel puntuaciones;
    protected Pelota pelota;
    private Thread hiloBola;
    private Receiver receiver;
    private ReceiverTCP receiverTCP;
    protected boolean empezar = false;
    private boolean conexion1;
    private boolean conexion2;
    private int velocidad;
    private String[] datos;
    private String[] datosLargos;
    protected boolean continuar = true;

    public Vista() {
        conexion1 = false;
        conexion2 = false;

        velocidad = 0;

        escenario = new JPanel();
        puntuaciones = new JPanel();

        plataformaD = new JLabel(new ImageIcon(getClass().getResource("/padel.png")));
        plataformaI = new JLabel(new ImageIcon(getClass().getResource("/padel.png")));
        bola = new JLabel(new ImageIcon(getClass().getResource("/pelota.png")));

        plataformaI.setBounds(5, 20, 15, 51);
        plataformaD.setBounds(760, 470, 15, 51);
        bola.setBounds(350, 350, 25, 25);

        puntosD = new JLabel("PUNTOS:");
        puntosI = new JLabel("PUNTOS:");

        numeroD = new JLabel("0");
        numeroI = new JLabel("0");

        setLayout(new BorderLayout());
        escenario.setLayout(null);
        escenario.setBackground(Color.blue);
        puntuaciones.setLayout(new GridLayout(1, 2));
        puntuaciones.add(puntosI);
        puntuaciones.add(numeroI);
        puntuaciones.add(puntosD);
        puntuaciones.add(numeroD);

        add(escenario, BorderLayout.CENTER);
        add(puntuaciones, BorderLayout.SOUTH);
        escenario.add(plataformaD);
        escenario.add(plataformaI);
        escenario.add(bola);




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pong Game");
        setSize(800, 600);
        setVisible(true);
        
        receiver = new Receiver(this);
        Thread t = new Thread(receiver);
        t.start();

        receiverTCP = new ReceiverTCP(this);
        Thread t2 = new Thread(receiverTCP);
        t2.start();

    }

    public void pintarBola(Pelota bola) {
        this.bola.setBounds(bola.getPosX(), bola.getPosY(), 25, 25);
    }

    public static void main(String[] args) {
        Vista v = new Vista();
    }

    public void moverPadel(String numPad, String mov) {
       // System.out.println("numPad= "+numPad+"  mov= "+mov);
        mov = mov.trim();
        numPad = numPad.trim();
        if (numPad.equals("p1")) {
            if (mov.equals("w")) {
                if(plataformaI.getLocation().y>7)
                plataformaI.setLocation(plataformaI.getLocation().x, plataformaI.getLocation().y - 5);
            } else if(mov.equals("s")){
                if(plataformaI.getLocation().y<480)
                plataformaI.setLocation(plataformaI.getLocation().x, plataformaI.getLocation().y + 5);
            }
        } else if(numPad.equals("p2")){
            if (mov.equals("8")){
                if(plataformaD.getLocation().y>7)
                plataformaD.setLocation(plataformaD.getLocation().x, plataformaD.getLocation().y - 5);
            }else if(mov.equals("2")){
                if(plataformaD.getLocation().y<480)
                plataformaD.setLocation(plataformaD.getLocation().x, plataformaD.getLocation().y + 5);
            }
        }
    }

    public void ejecutarBola(int velocidad){
        if(empezar) {
            pelota = new Pelota(800, 570, velocidad, this);  //llamar con ese tamaño porque lo llamas para setear los márgenes
            hiloBola = new Thread(new PelotaThread(this, pelota)); //la bola empieza a moverse si o si
            hiloBola.start();
        }
    }

    public void procesaMsg(String msg){
        datos = msg.split("#");

        if(datos.length>2) {
            datosLargos=datos;
            this.puntosI.setText(datos[2].toUpperCase(Locale.ROOT));
            velocidad = Integer.parseInt(datos[0]);
            conexion1 = true;
        }else{
            this.puntosD.setText(datos[0].toUpperCase(Locale.ROOT));
            conexion2 = true;
        }
        if(conexion1 && conexion2) {
            empezar = true;
            this.ejecutarBola(velocidad);
        }
    }

    public void arbitro(){
        int limPuntos = Integer.parseInt(datosLargos[1]);
        if(Integer.parseInt(numeroD.getText())==limPuntos){
            continuar = false;
        }else if(Integer.parseInt(numeroI.getText())==limPuntos){
            continuar = false;
        }
    }
}
