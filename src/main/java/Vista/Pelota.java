package Vista;

public class Pelota {
    private int posX, posY, dirX, dirY, radio=10, margenX, margenY, velocidad;

    public Pelota(int margenX, int margenY) {
        this.margenX = margenX-45;
        this.margenY = margenY-45;
        posX = 350;
        posY = 250;
        dirX = velocidad + generarDireccion();
        dirY = velocidad + generarDireccion();
    }
    
    private int generarDireccion() {
        byte num = (byte) (Math.random()*256-128);
        if (num>0){
            return 1;
        } else {
            return -1;
        }
    }    
    
    public void moverBola() {
        posX += dirX;
        posY += dirY;
        
        if ((posX) <-1) {
            dirX*=-1;
        } else if ((posX/*+radio*/) >= margenX)  {
            dirX*=-1;
        }
        
        if ((posY) <-1) {
            dirY*=-1;
        } else if ((posY/*+radio*/) >= margenY)  {
            dirY*=-1;
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getMargenX() {
        return margenX;
    }

    public void setMargenX(int margenX) {
        this.margenX = margenX;
    }

    public int getMargenY() {
        return margenY;
    }

    public void setMargenY(int margenY) {
        this.margenY = margenY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
