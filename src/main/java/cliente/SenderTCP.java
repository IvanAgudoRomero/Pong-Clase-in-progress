package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SenderTCP {
    private Socket clientSocket;
    private PrintWriter out;
    private VCliente vc;
    private VCliente2 vc2;
    private String ip;
    private int port;

    public SenderTCP(VCliente vc, String ip, int port) {
        this.vc=vc;
        this.ip=ip;
        this.port=port;
        clientSocket = null;
        out = null;
        
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(getDatos());
        } catch (IOException ex) {
            Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            out.close();
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SenderTCP(VCliente2 vc2, String ip, int port) {
        this.vc2=vc2;
        this.ip=ip;
        this.port=port;
        clientSocket = null;
        out = null;

        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(getDatos2());
        } catch (IOException ex) {
            Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            out.close();
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getDatos(){
        String datos = null;
        datos = vc.getVelocidad().getText().trim()+"#"+vc.getGoles().getText().trim()+"#"+ vc.getNombre().getText().trim()+"#"+vc.getJugador().getText().trim();
        return datos;
    }

    public String getDatos2(){
        String datos = null;
        datos = vc2.getNombre().getText().trim()+"#"+vc2.getJugador().getText().trim();
        return datos;
    }
}
