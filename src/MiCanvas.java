import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class MiCanvas extends Canvas implements KeyListener, ActionListener {
    private Jugador usuario;
    private Nivel lvl;
    private Timer relojUpdate;
    ArrayList<Enemigo> enemigos;

    private BufferedImage imagen;


    public MiCanvas() {
        super();
        this.setFocusable(true);
        enemigos = new ArrayList<>(10);
        usuario = new Jugador(1, 50, 50, 50, "Martin");

        lvl = new Nivel();

        //enemigos.add(new Enemigo(1, 525, -100, 50, 3000, 3));
        //enemy = new Enemigo(1,50,200,50, 50, 1, 10, 10);


        addKeyListener(this);
        relojUpdate = new Timer(10, this);
        imagen = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_RGB);

        relojUpdate.start();
    }

    public void paint(Graphics g) {
        Graphics gra = imagen.createGraphics();
        gra.setColor(Color.white);
        gra.fillRect(0, 0, this.getWidth(), this.getHeight());
        lvl.paint(gra);

        ListIterator<Enemigo> itrE = enemigos.listIterator();
        while (itrE.hasNext()) {
            itrE.next().paint(gra);
        }

        usuario.paint(gra);

        g.drawImage(imagen, 0, 0, null);
    }

    public void update(Graphics g) {

        usuario.gravity(lvl.getObs());
        usuario.mover(lvl.getObs());

        //System.out.println(usuario.getPosicionX() + ", " + usuario.getVelocidadX());

        if(usuario.getPosicionX()==1150 && enemigos.size()==0){
            nextLevel();
        }

        if(usuario.getBala().colision(lvl.getObs())){
            usuario.getBala().setActive(false);
        }

        ArrayList<Enemigo> remove = new ArrayList<>(5);

        ListIterator<Enemigo> itrE = enemigos.listIterator();

        while (itrE.hasNext()) {
            Enemigo tmp = itrE.next();

            tmp.gravity(lvl.getObs());
            tmp.mover(lvl.getObs());

            if(tmp.impactoProyectil(usuario.getBala())){
                usuario.getBala().setActive(false);
                remove.add(tmp);
            }
            tmp.setFacingF(tmp.getPosicionX()<usuario.getPosicionX());

            if(usuario.impactoProyectil(tmp.getBala())){
                tmp.getBala().setActive(false);
                usuario.die();
            }
            if(tmp.getBala().colision(lvl.getObs())){
                tmp.getBala().setActive(false);
            }
        }

        ListIterator<Enemigo> itrR = remove.listIterator();
        while (itrR.hasNext()) {
            enemigos.remove(itrR.next());
        }

        if(usuario.getVida()<=0){
            relojUpdate.stop();
        }

        paint(g);
    }

    public void nextLevel(){
        enemigos.clear();
        lvl.nextLvl();
        usuario.setPosicionX(50);

        Random r = new Random();
        int noEnemigos = 1;
        noEnemigos = lvl.getNivel()+r.nextInt(3)-1;
        if(noEnemigos>=10){
          noEnemigos=10;
        } else if(noEnemigos<=0){
            noEnemigos=1;
        }

        for (int i = 0; i<noEnemigos; i++){
            int disp = r.nextInt(10-noEnemigos)+500;
            int vel = 3-disp/1000;

            enemigos.add(new Enemigo(i, r.nextInt(950)+200, -100, r.nextInt(20)+40, disp, vel));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        37 izquierda
		38 arriba
		39 derecha
		40 abajo
         */
        if(relojUpdate.isRunning()) {
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
