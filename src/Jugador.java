import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Jugador extends Persona {
    private String nombre;
    private int vida = 10;

    public Jugador(int id, int posicionX, int posicionY, int tamanio, String nombre) {
        super(id, posicionX, posicionY, tamanio);
        this.nombre = nombre;
        this.velocidadX = 0;
    }


    @Override
    public void die() {
        vida--;
    }

    public void paint(Graphics g) {
        bala.paint(g);
        if (facingF) {
            g.setColor(Color.GRAY);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.black);
            g.fillRect(posicionX+tamanio-18, posicionY+10, 15, 15);
            g.fillRect(posicionX+tamanio-37, posicionY+10, 15, 15);
            g.fillRect(posicionX+tamanio-15, posicionY+13, 15, 3);
            g.fillRect(posicionX, posicionY+13, 25, 3);
            g.fillRect(posicionX+tamanio-24, posicionY+11, 15, 2);
            g.fillRect(posicionX + tamanio / 2-10, posicionY + tamanio / 2+5, tamanio / 2, tamanio / 10);
            g.fillRect(posicionX + tamanio / 2-10, posicionY + tamanio / 2+5, 5, 15);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.black);
            g.fillRect(posicionX+3, posicionY+10, 15, 15);
            g.fillRect(posicionX+22, posicionY+10, 15, 15);
            g.fillRect(posicionX, posicionY+13, 15, 3);
            g.fillRect(posicionX+tamanio-25, posicionY+13, 25, 3);
            g.fillRect(posicionX+9, posicionY+11, 15, 2);
            g.fillRect(posicionX + tamanio / 2-10, posicionY + tamanio / 2+5, tamanio / 2, tamanio / 10);
            g.fillRect(posicionX + tamanio / 2+10, posicionY + tamanio / 2+5, 5, 15);
        }
    }

    public void moveF(boolean activ) {
        if (activ) {
            facingF = true;
            setVelocidadX(VX);
        } else {
            setVelocidadX(0);
        }
    }

    public void moveB(boolean activ) {
        if (activ) {
            facingF = false;
            setVelocidadX(-VX);
        } else {
            setVelocidadX(0);
        }
    }

    public void mover(ArrayList<Obstaculos> obs) {
        //Rectangle u = new Rectangle(posicionX,posicionY,tamanio,tamanio);
        if ((!colisionLeft(obs) && !facingF) || (!colisionRight(obs) && facingF)) {
            posicionX += velocidadX;
        } else {
            velocidadX = 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
