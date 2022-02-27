package Vista;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PelotaThread implements Runnable{
    private Vista v;
    private Pelota pelota;

    public PelotaThread(Vista v, Pelota pelota) {
        this.v = v;
        this.pelota = pelota;
    }        
    
    @Override
    public void run() {
        while(v.continuar){ //poner v.variable booleana
            pelota.moverBola();
            v.pintarBola(pelota);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(PelotaThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
