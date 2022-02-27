package cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VCliente2 extends JFrame implements KeyListener {
    private JTextField ip, puerto, nombre, jugador;
    private JLabel lbIp, lbPuerto, lbNombre, lbJugador, vacio;
    private  JButton aceptar;
    private Sender sender;
    private SenderTCP senderTCP;
    private boolean ready;
    private VCliente2 esto;

    protected String msg;

    public VCliente2() {
        msg = " ";
        ready = false;

        ip = new JTextField();
        puerto = new JTextField();
        lbIp = new JLabel();
        lbPuerto = new JLabel();
        vacio = new JLabel();
        aceptar = new JButton();
        lbNombre = new JLabel();
        nombre = new JTextField();
        jugador = new JTextField();
        lbJugador = new JLabel();

        lbIp.setText("IP: ");
        lbIp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbPuerto.setText("Puerto: ");
        lbPuerto.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbNombre.setText("Nombre: ");
        lbNombre.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbJugador.setText("Padel(p1/p2): ");
        lbJugador.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        aceptar.setText("Aceptar");

        this.addKeyListener(this);
        this.setFocusable(true);

        setLayout(new GridLayout(5,2));

        add(lbIp);
        add(ip);
        add(lbPuerto);
        add(puerto);
        add(lbNombre);
        add(nombre);
        add(lbJugador);
        add(jugador);

        add(vacio);
        add(aceptar);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ready = true;
                System.out.println("Hola XD v2");
                senderTCP = new SenderTCP(esto, ip.getText().trim(), Integer.parseInt(puerto.getText().trim()));
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cliente2");
        setSize(300,300);
        setVisible(true);

        sender = new Sender(this);
        Thread t = new Thread(sender);
        t.start();

        esto=this;
    }

    public static void main(String[] args) {
        VCliente2 vc2 = new VCliente2();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (ready) {
            //poner primero el padel porque se procesa como primero
            System.out.println(jugador.getText()+"#"+arg0.getKeyChar());
            this.msg = jugador.getText()+"#"+arg0.getKeyChar();
            //sender.msg = arg0.getKeyCode() + "#" + ip; //enviar tambien el padel que eres y por ende el cliente que eres

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JTextField getPuerto() {
        return puerto;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getJugador() {
        return jugador;
    }
}
