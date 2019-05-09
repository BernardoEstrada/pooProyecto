import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;

public class Enemigo extends Persona implements ActionListener {
    private int velDisp;
    private Timer disp;

    public Enemigo() {
        super();
        this.velDisp = 3000;
        disp = new Timer(velDisp, this);
        disp.start();
    }

    public Enemigo(int posicionX, int posicionY) {
        super(posicionX, posicionY);
        this.velDisp = 3000;
        disp = new Timer(velDisp, this);
        disp.start();
    }

    public Enemigo(int id, int posicionX, int posicionY, int tamanio, int velDisp, int vx) {
        super(id, posicionX, posicionY, tamanio);
        this.velocidadX = vx;
        this.velDisp = velDisp;
        disp = new Timer(velDisp, this);
        disp.start();
    }

    @Override
    public void die() {

        Random random = new Random();
        this.posicionX = random.nextInt(1150);
        this.posicionY = 1;
        disp.stop();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == disp) {
            //if(new Random().nextBoolean()){ jump(); }
            disparar();
        }
    }

    public void paint(Graphics g) {
        bala.paint(g);
        if (facingF) {
            g.setColor(Color.orange);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.BLACK);
            g.fillRect(posicionX + tamanio / 2, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        } else {
            g.setColor(Color.orange);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.BLACK);
            g.fillRect(posicionX, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        }
    }

    public void mover(ArrayList<Obstaculos> obs) {
        gravity(obs);
        if (velocidadY <= 0.2) {
            posicionX += velocidadX;
            if(onEdgeL(obs)) {
                velocidadX = Math.abs(velocidadX);
            } else if(onEdgeR(obs)){
                velocidadX = -Math.abs(velocidadX);
            }
        }
    }

    private boolean onEdgeL(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if (posicionY + tamanio == tmp.getPosicionY()){
                if((posicionX + tamanio / 2) <= tmp.getPosicionX()){
                    return true;
                }
            }
        }
        return colisionLeft(obs);
    }
    private boolean onEdgeR(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if (posicionY + tamanio == tmp.getPosicionY()){
                if((posicionX + tamanio / 2) >= tmp.getPosicionX() + tmp.getTamanioX()){
                    return true;
                }
            }
        }
        return colisionRight(obs);
    }

    public int getVelDisp() {
        return velDisp;
    }

    public void setVelDisp(int velDisp) {
        this.velDisp = velDisp;
    }

    public Timer getDisp() {
        return disp;
    }

    public void setDisp(Timer disp) {
        this.disp = disp;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Enemigo && ((Enemigo) o).getId() == this.id);
    }

    @Override
    public String toString() {
        return "Enemigo{" +
                "velDisp=" + velDisp +
                ", disp=" + disp +
                ", VY=" + VY +
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
