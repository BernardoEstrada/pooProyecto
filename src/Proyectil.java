import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Proyectil implements ActionListener {
    private int posicionX, posicionY, radio, velocidadX;
    private boolean active, direccion;
    private Timer reloj;

    public Proyectil(int radio, int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.radio = radio;
        this.velocidadX = 10;
        active = false;
        direccion = true;
        reloj = new Timer(10, this);
    }

    public void paint(Graphics g) {
        if (active) {
            g.setColor(Color.GRAY);
            g.fillOval(posicionX, posicionY, radio, radio);
        }
    }

    public void avanzar() {
        if (direccion) {
            posicionX += velocidadX;
        } else {
            posicionX -= velocidadX;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == reloj) {
            if (active) {
                avanzar();
            }
        }

    }

    public boolean colision(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if((posicionX<tmp.getPosicionX()+tmp.getTamanioX() && posicionX+radio>tmp.getPosicionX() && (posicionY<tmp.getPosicionY()+tmp.getTamanioY() && posicionY+radio>tmp.getPosicionY())) || (posicionX + radio + velocidadX >= 800) || (posicionX + velocidadX < 0)) {
                return true;
            }
        }
        return false;
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

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            reloj.start();
        } else {
            reloj.stop();
        }
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }
}
