import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class MiCanvas extends Canvas implements KeyListener, ActionListener {
    private Jugador usuario;
    private Nivel lvl;
    private Timer relojUpdate;
    private ArrayList<Enemigo> enemigos;

    private BufferedImage imagen;
    private Image bg, img1, img2, img3;


    public MiCanvas() {
        super();
        this.setFocusable(true);
        enemigos = new ArrayList<>(10);
        usuario = new Jugador(1, 0, 400, 50, "Bernardo");

        lvl = new Nivel();
        try {
            bg = ImageIO.read(new File("images/bg2.jpg"));
            img1 = ImageIO.read(new File("images/bg2.jpg"));
            img2 = ImageIO.read(new File("images/bg3.jpg"));
            img3 = ImageIO.read(new File("images/bg4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //enemigos.add(new Enemigo(1, 525, -100, 50, 3000, 3));
        //enemy = new Enemigo(1,50,200,50, 50, 1, 10, 10);


        addKeyListener(this);
        relojUpdate = new Timer(10, this);
        imagen = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_RGB);

        relojUpdate.start();
    }

    /**
     * @param g of Graphics class
     */
    public void paint(Graphics g) {
        Graphics gra = imagen.createGraphics();
        gra.drawImage(bg, 0, 0, 1200, 600, this);
        lvl.paint(gra);
        Hud.paint(gra, usuario.getVida(), lvl.getNivel());

        ListIterator<Enemigo> itrE = enemigos.listIterator();
        while (itrE.hasNext()) {
            itrE.next().paint(gra);
        }

        usuario.paint(gra);


        if (!relojUpdate.isRunning()) {
            gra.setColor(new Color(0, 0, 0, 100));
            gra.fillRect(0, 0, 1200, 600);
            gra.setColor(Color.RED);
            gra.setFont(new Font("TimesRoman", Font.BOLD, 180));
            gra.drawString("You Died", getWidth() / 2 - 400, getHeight() / 2 + 50);

            gra.setColor(Color.WHITE);
            gra.setFont(new Font("TimesRoman", Font.BOLD, 40));
            gra.drawString("Press space to restart", getWidth() / 2 - 200, getHeight() / 2 + 100);
        }
        g.drawImage(imagen, 0, 0, null);
    }

    /**
     * This function updates the canvas, letting the objects move smoothly
     *
     * @param g
     */
    public void update(Graphics g) {

        usuario.gravity(lvl.getObs());
        usuario.mover(lvl.getObs());

        if (usuario.getPosicionX() == 1150 && enemigos.size() == 0) {
            nextLevel();
        }

        if (usuario.getBala().colision(lvl.getObs())) {
            usuario.getBala().setActive(false);
        }

        ArrayList<Enemigo> remove = new ArrayList<>(5);

        ListIterator<Enemigo> itrE = enemigos.listIterator();

        while (itrE.hasNext()) {
            Enemigo tmp = itrE.next();

            tmp.mover(lvl.getObs());

            if (tmp.impactoProyectil(usuario.getBala())) {
                usuario.getBala().setActive(false);
                remove.add(tmp);
            }
            if (tmp.getVelDisp() > 600) {
                tmp.setFacingF(tmp.getPosicionX() < usuario.getPosicionX());
            } else {
                tmp.setFacingF(false);
            }

            if (usuario.impactoProyectil(tmp.getBala())) {
                tmp.getBala().setActive(false);
                usuario.die();
            }
            if (tmp.getBala().colision(lvl.getObs())) {
                tmp.getBala().setActive(false);
            }
        }

        ListIterator<Enemigo> itrR = remove.listIterator();
        while (itrR.hasNext()) {
            enemigos.remove(itrR.next());
        }

        if (usuario.getVida() <= 0) {
            relojUpdate.stop();
        }

        paint(g);
    }

    /**
     * This function generates a new level once the user defeats all enemies and reaches the end of the screen
     */
    public void nextLevel() {
        enemigos.clear();
        lvl.nextLvl();
        usuario.setPosicionX(50);


        switch (new Random().nextInt() % 3) {
            case 0:
                bg = img1;
                break;
            case 1:
                bg = img2;
                break;
            case 2:
                bg = img3;
                break;
            default:
                break;
        }


        Random r = new Random();
        int noEnemigos = 1;
        int niv = lvl.getNivel();
        noEnemigos = niv + r.nextInt(3) - 1;
        if (niv > 10) {
            niv = 10;
        }
        if (noEnemigos >= 10) {
            noEnemigos = 10;
        } else if (noEnemigos <= 0) {
            noEnemigos = 1;
        }

        for (int i = 0; i < noEnemigos; i++) {
            int disp = Math.abs((10 - niv) * 500 + r.nextInt(1000) - 1500);

            double vel;
            if (disp <= 800) {
                vel = 0;
            } else {
                vel = (double) niv / 2 - (double) disp / 2000 - r.nextDouble();
            }
            enemigos.add(new Enemigo(i, r.nextInt(950) + 200, -100, r.nextInt(20) + 40, disp, (int) Math.round(vel)));
        }

    }

    /**
     * This function resets the objects in the game
     */
    public void reset() {
        enemigos = new ArrayList<>(10);
        usuario = new Jugador(1, 0, 400, 50, "Bernardo");

        lvl = new Nivel();
        relojUpdate.start();
    }


    /**
     * Function of the KeyListener class, allos the user to move with the keyboard
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        /*
        37 izquierda
		38 arriba
		39 derecha
		40 abajo
         */
        int pressed = e.getKeyCode();
        if (relojUpdate.isRunning()) {

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
        } else if (pressed == 32) {
            reset();
        }
    }

    /**
     * This function works so the side movement is smooth, one the key is held
     *
     * @param e
     */
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

    /**
     * This function continuosly repaints the canvas.
     *
     * @param evento
     */
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == relojUpdate) {
            repaint();
        }
    }
}
