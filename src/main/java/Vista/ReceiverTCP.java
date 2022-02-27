package Vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiverTCP implements Runnable{
    private Vista v;
    private int port = 2045;
    private ServerSocket serverSocket;

    public ReceiverTCP(Vista v) {
        this.v = v;
    }
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(new Aceptador(v, serverSocket.accept()));
            t.start();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
