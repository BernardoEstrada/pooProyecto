import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public abstract class Persona {

    protected final int VY = 10, VX = 5;
    protected final double GRAVITY = 0.2;

    protected int id, posicionX, posicionY, tamanio, velocidadX, jumping;
    protected double velocidadY;
    protected boolean facingF;
    protected Proyectil bala;

    public Persona() {
        id = 0;
        posicionX = 100;
        posicionY = 100;
        tamanio = 50;
        velocidadX = 0;
        velocidadY = 0;
        jumping = 0;
        facingF = true;
        bala = new Proyectil(10, this.getPosicionX(), this.getPosicionY());
    }

    public Persona(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        tamanio = 50;
        velocidadX = 0;
        velocidadY = 0;
        jumping = 0;
        facingF = true;
        id = 0;
        bala = new Proyectil(10, this.getPosicionX(), this.getPosicionY());


    }

    public Persona(int id, int posicionX, int posicionY, int tamanio) {
        this.id = id;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tamanio = tamanio;
        this.velocidadX = VX;
        this.velocidadY = VY;
        jumping = 0;
        facingF = true;
        bala = new Proyectil(10, this.getPosicionX(), this.getPosicionY());
    }

    public boolean impactoProyectil(Proyectil pro){
        return (pro.getPosicionX()>posicionX && pro.getPosicionX()<posicionX+tamanio && pro.getPosicionY()>posicionY && pro.getPosicionY()<posicionY+tamanio && pro.isActive());
    }

    public abstract void die();
    public abstract void mover(ArrayList<Obstaculos> obs);

    public boolean disparar() {
        if (!bala.isActive()) {
            bala.setDireccion(facingF);
            bala.setPosicionX(posicionX + tamanio / 2);
            bala.setPosicionY(posicionY + tamanio / 2);
            bala.setActive(true);
            return true;
        }
        return false;
    }

    public boolean colisionDown(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if (posicionY + tamanio + velocidadY > tmp.getPosicionY() && posicionY + velocidadY < tmp.getPosicionY() + tmp.getTamanioY() && posicionX + tamanio > tmp.getPosicionX() && posicionX < tmp.getPosicionX() + tmp.getTamanioX()) {
                return false;
            }
        }
        return true;
    }

    public boolean colisionLeft(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if ((posicionX + velocidadX < tmp.getPosicionX() + tmp.getTamanioX() && posicionX + velocidadX > tmp.getPosicionX() && posicionY + tamanio > tmp.getPosicionY() && posicionY < tmp.getPosicionY() + tmp.getTamanioY()) || posicionX + velocidadX < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean colisionRight(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if ((posicionX + tamanio + velocidadX > tmp.getPosicionX() && posicionX + tamanio + velocidadX < tmp.getPosicionX() + tmp.getTamanioX() && posicionY + tamanio > tmp.getPosicionY() && posicionY < tmp.getPosicionY() + tmp.getTamanioY()) || posicionX + tamanio + VX > 1200) {
                return true;
            }
        }
        return false;
    }

    public void gravity(ArrayList<Obstaculos> obs) {
        posicionY += velocidadY;
        if (colisionDown(obs)) {
            velocidadY += GRAVITY;
        } else {
            velocidadY = 0;
            jumping = 0;
        }
    }

    public void jump() {
        if (jumping < 2) {
            setJumping(jumping + 1);
            setVelocidadY(-6);
        }
    }

    public abstract void paint(Graphics g);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public double getGRAVITY() {
        return GRAVITY;
    }

    public int getJumping() {
        return jumping;
    }

    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    public boolean isFacingF() {
        return facingF;
    }

    public void setFacingF(boolean facingF) {
        this.facingF = facingF;
    }

    public int getVX() {
        return VX;
    }

    public int getVY() {
        return VY;
    }

    public Proyectil getBala() {
        return bala;
    }

    public void setBala(Proyectil bala) {
        this.bala = bala;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "VY=" + VY +
                ", VX=" + VX +
                ", GRAVITY=" + GRAVITY +
                ", id=" + id +
                ", posicionX=" + posicionX +
                ", posicionY=" + posicionY +
                ", tamanio=" + tamanio +
                ", velocidadX=" + velocidadX +
                ", jumping=" + jumping +
                ", velocidadY=" + velocidadY +
                ", facingF=" + facingF +
                ", bala=" + bala +
                '}';
    }
}
