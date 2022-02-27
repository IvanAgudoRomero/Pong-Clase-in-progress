/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vespertino
 */
public class Sender implements Runnable{
    public String msg ="void";
    public InetAddress ip;
    public int port;
    public DatagramSocket mySocket;
    public byte[] buffer;
    public DatagramPacket datagram;
    public VCliente c;
    public VCliente2 c2;

    public Sender(VCliente c) {
        try {                  
            this.c=c;
            ip = InetAddress.getByName("127.0.0.1");            
            port = 2045;
            mySocket = new DatagramSocket();
           
        
        } catch (SocketException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Sender(VCliente2 c) {
        try {
            this.c2=c;
            ip = InetAddress.getByName("127.0.0.1");
            port = 2045;
            mySocket = new DatagramSocket();


        } catch (SocketException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if(c==null){
                    buffer = c2.msg.getBytes();
                    datagram = new DatagramPacket(buffer, buffer.length, ip, port);
                    mySocket.send(datagram);
                    Thread.sleep(100);
                }else {
                    buffer = c.msg.getBytes();
                    //enviamos msg cada x tiempo
                    datagram = new DatagramPacket(buffer, buffer.length, ip, port);
                    mySocket.send(datagram);
                    Thread.sleep(100);
                }
            } catch (IOException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
               // mySocket.close();
            }
        }
    }
    
}
