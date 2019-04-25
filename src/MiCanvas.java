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
    private Enemigo enemy;
    private Timer relojGra;
    ArrayList<Obstaculos> obs;

    private BufferedImage imagen;


    public MiCanvas(){
        super();
        this.setFocusable(true);
        obs = new ArrayList<>(5);
        usuario = new Jugador(1,50,50,50,"Martin");
        enemy = new Enemigo(1,50,525,50, 50, 1, 10, 10);
        //enemy = new Enemigo(1,50,200,50, 50, 1, 10, 10);

        obs.add(new Obstaculos(2,0,300,800,100));
        obs.add(new Obstaculos(3,300,200,100,100));
        obs.add(new Obstaculos(4,500,150,100,50));
        addKeyListener(this);
        relojGra = new Timer(10, this);
        imagen = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);

        relojGra.start();
    }

    public void paint(Graphics g){
        Graphics gra = imagen.createGraphics();
        gra.setColor(Color.white);
        gra.fillRect(0, 0, this.getWidth(), this.getHeight());
        ListIterator<Obstaculos> itr = obs.listIterator();
        while (itr.hasNext()){
            itr.next().paint(gra);
        }
        usuario.paint(gra);
        enemy.paint(gra);

        relojGra.start();

        g.drawImage(imagen, 0, 0, null);
    }

    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void keyPressed(KeyEvent e){
        int pressed = e.getKeyCode();
        System.out.println(pressed);
        usuario.mover(pressed, obs);

        if(pressed == 32){
            usuario.disparar();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public void actionPerformed(ActionEvent evento) {
        /*
        if(evento.getSource() == reloj) {
                if(usuario.getBala().getPosicionX()>enemy.getPosicionX() && usuario.getBala().getPosicionX()<enemy.getPosicionX()+enemy.getTamanio() && usuario.getBala().getPosicionY()>enemy.getPosicionY() && usuario.getBala().getPosicionY()<enemy.getPosicionY()+enemy.getTamanio()){
                    Random random = new Random();
                    enemy = new Enemigo(random.nextInt(750),1);
                    reloj.stop();
                    usuario.getBala().setActive(false);
                }
            } else {
                reloj.stop();
            }
        }*/
        if (evento.getSource() == relojGra){
            usuario.gravity(obs);
            enemy.gravity(obs);
        }
        repaint();

    }

}
