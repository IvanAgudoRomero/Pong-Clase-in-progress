package Vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Aceptador implements Runnable {
    private Vista v;
    private Socket socket;
    private BufferedReader br;
    private String inputLine;

    public Aceptador(Vista v, Socket socket) {
        this.v = v;
        this.socket = socket;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            inputLine = br.readLine();
            v.procesaMsg(inputLine);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
