import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;

public class MiCanvas extends Canvas implements KeyListener, ActionListener {
    private int nivel;
    private Jugador usuario;
    private Timer relojUpdate;
    ArrayList<Obstaculos> obs;
    ArrayList<Enemigo> enemigos;

    private BufferedImage imagen;


    public MiCanvas() {
        super();
        nivel = 0;
        this.setFocusable(true);
        obs = new ArrayList<>(5);
        enemigos = new ArrayList<>(5);
        usuario = new Jugador(1, 50, 50, 50, "Martin");

        enemigos.add(new Enemigo(1, 50, 525, 50, 50, 3));
        enemigos.add(new Enemigo(2, 50, 100, 50, 50, 1));
        //enemy = new Enemigo(1,50,200,50, 50, 1, 10, 10);

        obs.add(new Obstaculos(1, 0, 500, 1200, 100));
        obs.add(new Obstaculos(2, 300, 400, 100, 100));
        obs.add(new Obstaculos(3, 500, 350, 100, 50));


        addKeyListener(this);
        relojUpdate = new Timer(10, this);
        imagen = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_RGB);

        relojUpdate.start();
    }

    public void paint(Graphics g) {
        Graphics gra = imagen.createGraphics();
        gra.setColor(Color.white);
        gra.fillRect(0, 0, this.getWidth(), this.getHeight());
        ListIterator<Obstaculos> itrO = obs.listIterator();
        while (itrO.hasNext()) {
            itrO.next().paint(gra);
        }
        ListIterator<Enemigo> itrE = enemigos.listIterator();
        while (itrE.hasNext()) {
            itrE.next().paint(gra);
        }

        usuario.paint(gra);

        g.drawImage(imagen, 0, 0, null);
    }

    public void update(Graphics g) {

        usuario.gravity(obs);
        usuario.mover(obs);

        //System.out.println(usuario.getPosicionX() + ", " + usuario.getVelocidadX());
/*
        if(usuario.getPosicionX()==1150){
            nextLevel();
        }
*/
        if(usuario.getBala().colision(obs)){
            usuario.getBala().setActive(false);
        }


        ListIterator<Enemigo> itrE = enemigos.listIterator();
        while (itrE.hasNext()) {
            Enemigo tmp = itrE.next();
            tmp.gravity(obs);
            tmp.mover(obs);
            if(tmp.impactoProyectil(usuario.getBala())){
                usuario.getBala().setActive(false);
                tmp.die();
            }
            ListIterator<Enemigo> itrE2 = enemigos.listIterator();
            while (itrE2.hasNext()) {
                Enemigo tmp2 = itrE2.next();
                if(tmp.impactoProyectil(tmp2.getBala()) && !tmp.equals(tmp2)) {
                    tmp2.getBala().setActive(false);
                    tmp.die();
                }
            }
            tmp.setFacingF(tmp.getPosicionX()<usuario.getPosicionX());

            if(tmp.getBala().colision(obs)){
                tmp.getBala().setActive(false);
            }
            if(usuario.impactoProyectil(tmp.getBala())){
                tmp.getBala().setActive(false);
                usuario.die();
            }
        }




        paint(g);
    }

    public void nextLevel(){
        nivel++;
        enemigos.clear();
        obs.clear();
        obs.add(new Obstaculos(2, 0, 500, 1200, 100));
        usuario.setPosicionX(50);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        37 izquierda
		38 arriba
		39 derecha
		40 abajo
         */

        int pressed = e.getKeyCode();

        if (pressed == 37) {
            usuario.moveB(true);
        }
        if (pressed == 39) {
            usuario.moveF(true);
        }
        if (pressed == 38) {
            usuario.jump();
        }
        if (pressed == 32) {
            usuario.disparar();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int released = e.getKeyCode();

        if (released == 37) {
            usuario.moveB(false);
        }
        if (released == 39) {
            usuario.moveF(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == relojUpdate) {
            repaint();
        }

    }

}
