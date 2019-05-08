import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Jugador extends Persona {
    private String nombre;
    private int vida = 5;

    public Jugador(int id, int posicionX, int posicionY, int tamanio, String nombre) {
        super(id, posicionX, posicionY, tamanio);
        this.nombre = nombre;
        this.velocidadX=0;
    }


    @Override
    public void die() {
        //System.out.println("You dead bro");
        vida--;
    }

    public void paint(Graphics g) {
        bala.paint(g);
        if (facingF) {
            g.setColor(Color.BLUE);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX + tamanio / 2, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
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
