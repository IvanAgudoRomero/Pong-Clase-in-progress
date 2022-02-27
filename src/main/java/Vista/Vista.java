package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vista extends JFrame {

    protected JLabel plataformaI, plataformaD, bola;
    protected JLabel puntosI, puntosD;
    private JPanel escenario;
    private JPanel puntuaciones;
    protected Pelota pelota;
    private Thread hiloBola;
    private Receiver receiver;
    private ReceiverTCP receiverTCP;
    protected boolean empezar = false;
    boolean conexion1;
    boolean conexion2;
    private int velocidad;

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

        puntosD = new JLabel("PUNTOS: 0");
        puntosI = new JLabel("PUNTOS: 0");

        setLayout(new BorderLayout());
        escenario.setLayout(null);
        escenario.setBackground(Color.blue);
        puntuaciones.setLayout(new GridLayout(1, 2));
        puntuaciones.add(puntosI);
        puntuaciones.add(puntosD);

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
                plataformaI.setLocation(plataformaI.getLocation().x, plataformaI.getLocation().y - 5);
            } else if(mov.equals("s")){
                plataformaI.setLocation(plataformaI.getLocation().x, plataformaI.getLocation().y + 5);
            }
        } else if(numPad.equals("p2")){
            if (mov.equals("8")){
                plataformaD.setLocation(plataformaD.getLocation().x, plataformaD.getLocation().y - 5);
            }else if(mov.equals("2")){
                plataformaD.setLocation(plataformaD.getLocation().x, plataformaD.getLocation().y + 5);
            }
        }
    }

    public void ejecutarBola(int velocidad){
        if(empezar) {
            pelota = new Pelota(800, 570, velocidad);  //llamar con ese tamaño porque lo llamas para setear los márgenes
            hiloBola = new Thread(new PelotaThread(this, pelota)); //la bola empieza a moverse si o si
            hiloBola.start();
        }
    }

    public void procesaMsg(String msg){
        String[] datos;
        datos = msg.split("#");

        if(datos.length>2) {
            this.puntosI.setText(datos[2].toUpperCase(Locale.ROOT)+": 0");
            velocidad = Integer.parseInt(datos[0]);
            conexion1 = true;
        }else{
            this.puntosD.setText(datos[0].toUpperCase(Locale.ROOT)+": 0");
            conexion2 = true;
        }
        if(conexion1 && conexion2) {
            empezar = true;
            this.ejecutarBola(velocidad);
        }
    }
}
