import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Enemigo extends Persona implements ActionListener {
    private final static int dispDelay = 3000;
    private Timer disp;

    public Enemigo() {
        super();
        disp = new Timer(dispDelay, this);
        disp.start();
    }

    public Enemigo(int posicionX, int posicionY) {
        super(posicionX, posicionY);
        disp = new Timer(dispDelay, this);
        disp.start();
    }

    public Enemigo(int id, int danio, int posicionX, int posicionY, int tamanio, int tipo, int velocidadX, int velocidadY) {
        super(id, danio, posicionX, posicionY, tamanio, tipo, velocidadX, velocidadY);
        disp = new Timer(dispDelay, this);
        disp.start();
    }

    @Override
    public void die() {
        Random random = new Random();
        this.posicionX = random.nextInt(750);
        this.posicionY = 1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == disp){
            if(new Random().nextBoolean()){ jump(); }
            System.out.println(bala.toString());
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
}
