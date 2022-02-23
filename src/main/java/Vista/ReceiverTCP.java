package Vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiverTCP implements Runnable{
    private Vista v;
    private int port = 2045;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private String inputLine;

    public ReceiverTCP(Vista v) {
        this.v = v;
    }
    
    @Override
    public void run() {
        serverSocket = null;
        clientSocket = null;
        in = null;
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            inputLine = in.readLine();
            procesaMsg(inputLine);
        } catch (IOException ex) {
            Logger.getLogger(ReceiverTCP.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                in.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ReceiverTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    public void procesaMsg(String msg){
        String[] datos = new String[3];
        datos = msg.split("#");

    }
}
