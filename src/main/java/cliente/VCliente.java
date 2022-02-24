/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VCliente extends JFrame implements KeyListener {

    private JTextField ip, puerto, velocidad, goles, nombre, jugador;
    private JLabel lbIp, lbPuerto, lbVacio, lbVelocidad, lbGoles, lbNombre, lbJugador;
    private JButton aceptar;
    private Sender sender;
    private SenderTCP senderTCP;
    private boolean ready;
    private VCliente esto;

    protected String msg;

    public VCliente() {
        msg = " ";
        ready = false;

        ip = new JTextField();
        puerto = new JTextField();
        lbIp = new JLabel();
        lbPuerto = new JLabel();
        lbVacio = new JLabel();
        aceptar = new JButton();
        lbVelocidad = new JLabel();
        lbGoles = new JLabel();
        lbNombre = new JLabel();
        velocidad = new JTextField();
        goles = new JTextField();
        nombre = new JTextField();
        jugador = new JTextField();
        lbJugador = new JLabel();

        lbIp.setText("IP: ");
        lbIp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbPuerto.setText("Puerto: ");
        lbPuerto.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbVelocidad.setText("Velocidad: ");
        lbVelocidad.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbGoles.setText("Límite de puntos: ");
        lbGoles.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbNombre.setText("Nombre: ");
        lbNombre.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lbJugador.setText("Padel(p1/p2): ");
        lbJugador.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        aceptar.setText("Aceptar");

        this.addKeyListener(this);
        this.setFocusable(true);

        setLayout(new GridLayout(7, 2));
        add(lbIp);
        add(ip);
        add(lbPuerto);
        add(puerto);
        add(lbVelocidad);
        add(velocidad);
        add(lbGoles);
        add(goles);
        add(lbNombre);
        add(nombre);
        add(lbJugador);
        add(jugador);

        add(lbVacio);
        add(aceptar);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ready = true;
                System.out.println("hola XD");
                senderTCP = new SenderTCP(esto, ip.getText().trim(), Integer.parseInt(puerto.getText().trim()));
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cliente");
        setSize(300, 300);
        setVisible(true);
        sender = new Sender(this);
        Thread t = new Thread(sender);
        t.start();
        esto = this;
    }

    public static void main(String[] args) {
        VCliente vc = new VCliente();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
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
    public void keyReleased(KeyEvent arg0) {
    }

    public JTextField getPuerto() {
        return puerto;
    }

    public JTextField getVelocidad() {
        return velocidad;
    }

    public JTextField getGoles() {
        return goles;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getJugador() {
        return jugador;
    }
}
