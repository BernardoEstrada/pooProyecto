import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Nivel {
    ArrayList<Obstaculos> obs;
    private int nivel;

    public Nivel() {
        obs = new ArrayList<>(11);

        nivel = -1;
        //obs.add(new Obstaculos(1, 0, 500, 1200, 100));
        //obs.add(new Obstaculos(2, 300, 400, 100, 100));
        //obs.add(new Obstaculos(3, 500, 350, 100, 50));
        //obs.add(new Obstaculos(4, 700, 200, 100, 50));
        nextLvl();
    }

    public void paint(Graphics g) {
        ListIterator<Obstaculos> itrO = obs.listIterator();
        while (itrO.hasNext()) {
            itrO.next().paint(g);
        }
    }

    public void nextLvl() {
        obs.clear();
        nivel++;
        obs.add(new Obstaculos(0, 0, 500, 1200, 100));
        switch (new Random().nextInt(5)) { //nivel%6 secuencia
            case 0:
                obs.add(new Obstaculos(1, 0, 250, 300, 50));
                obs.add(new Obstaculos(2, 300, 400, 100, 100));
                obs.add(new Obstaculos(3, 700, 200, 100, 50));
                obs.add(new Obstaculos(4, 900, 120, 100, 50));
                //obs.add(new Obstaculos(5, 500, 350, 100, 50));
                break;
            case 1:
                obs.add(new Obstaculos(1, 300, 400, 150, 100));
                obs.add(new Obstaculos(2, 450, 310, 150, 190));
                obs.add(new Obstaculos(3, 600, 200, 100, 300));
                obs.add(new Obstaculos(4, 700, 300, 210, 200));
                obs.add(new Obstaculos(5, 910, 350, 120, 150));
                obs.add(new Obstaculos(6, 1030, 250, 170, 250));
                break;
            case 2:
                obs.add(new Obstaculos(1, 100, 400, 100, 100));
                obs.add(new Obstaculos(2, 200, 300, 100, 200));
                obs.add(new Obstaculos(3, 300, 250, 100, 250));
                obs.add(new Obstaculos(4, 800, 350, 120, 150));
                obs.add(new Obstaculos(5, 920, 300, 120, 200));
                break;
            case 3:
                obs.add(new Obstaculos(1, 300, 200, 250, 50));
                obs.add(new Obstaculos(2, 700, 200, 250, 50));
                obs.add(new Obstaculos(3, 200, 350, 250, 50));
                obs.add(new Obstaculos(4, 600, 350, 250, 50));
                obs.add(new Obstaculos(5, 1000, 350, 250, 50));
                break;
            case 4:
                obs.add(new Obstaculos(1, 200, 400, 100, 100));
                obs.add(new Obstaculos(2, 700, 350, 150, 50));
                break;
            default:
                break;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Obstaculos> getObs() {
        return obs;
    }

    public void setObs(ArrayList<Obstaculos> obs) {
        this.obs = obs;
    }
}
