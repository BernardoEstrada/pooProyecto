import java.awt.*;

public class Proyectil {
    private int posicionX, posicionY, radio, velocidadX;
    private boolean active, direccion;

    public Proyectil(int radio, int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.radio = radio;
        this.velocidadX = 10;
        active = false;
        direccion = true;
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
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }
}
