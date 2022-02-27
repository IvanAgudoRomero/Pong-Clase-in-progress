package Vista;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver implements Runnable {

    private Vista v;
    private int port = 2045;
    private final int MAX_LEN = 800;
    private DatagramSocket mySocket = null;
    private byte[] buffer;
    private DatagramPacket datagram;
    private String msg;

    public Receiver(Vista v) {
        this.v = v;
    }

    @Override
    public void run() {
        String numpad, mov;
        try {
            mySocket = new DatagramSocket(port);
            buffer = new byte[MAX_LEN];
            while (true) {                                
                datagram = new DatagramPacket(buffer, MAX_LEN);
                mySocket.receive(datagram);
                //System.out.println("Recibido");
                msg = new String(buffer);
                //desglosar msg para el movimiento y el padel
                //System.out.println("el msg es:"+msg+"|");
                String[] datos = msg.split("#");
                if (datos.length>1) {                    
                    numpad = datos[0];
                    mov = datos[1];/*
                    if(v.plataformaI.getLocation().y>=470 || v.plataformaI.getLocation().y<=0){
                        numpad="";
                        mov="";
                    }   
                    if(v.plataformaD.getLocation().y>=470 || v.plataformaD.getLocation().y<=0){
                        numpad="";
                        mov="";                        
                    }*/
                    v.moverPadel(numpad, mov);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySocket.close();
        }
    }
}
