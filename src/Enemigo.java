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

    public Enemigo(int id, int danio, int posicionX, int posicionY, int tamanio, int velDisp) {
        super(id, danio, posicionX, posicionY, tamanio);
        this.velDisp = velDisp;
        disp = new Timer(velDisp * 1000, this);
        disp.start();
    }

    @Override
    public void die() {
        Random random = new Random();
        this.posicionX = random.nextInt(1150);
        this.posicionY = 1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == disp) {
            //if(new Random().nextBoolean()){ jump(); }
            disparar();
        }
    }

    public void paint(Graphics g) {
        if (facingF) {
            g.setColor(Color.RED);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.BLACK);
            g.fillRect(posicionX + tamanio / 2, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        } else {
            g.setColor(Color.RED);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.BLACK);
            g.fillRect(posicionX, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        }
        bala.paint(g);
    }

    public void mover(ArrayList<Obstaculos> obs) {
        if (velocidadY <= 0.2) {
            posicionX += velocidadX;
            if (onEdge(obs)) {
                velocidadX = -velocidadX;
            }
        }
    }

    private boolean onEdge(ArrayList<Obstaculos> obs) {
        ListIterator itr = obs.listIterator();
        Obstaculos tmp;
        while (itr.hasNext()) {
            tmp = (Obstaculos) itr.next();
            if (posicionY + tamanio == tmp.getPosicionY() && ((posicionX + tamanio / 2) < tmp.getPosicionX() || (posicionX + tamanio / 2) > tmp.getPosicionX() + tmp.getTamanioX())) {
                return true;
            }
        }
        return (colisionRight(obs) || colisionLeft(obs));
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Enemigo && ((Enemigo) o).getId() == this.id);
    }
}
