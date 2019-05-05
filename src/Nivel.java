import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Nivel {
    ArrayList<Obstaculos> obs;
    private int nivel;

    public Nivel(){
        obs = new ArrayList<>(5);
        nextLvl();
    }

    public void paint(Graphics g){
        ListIterator<Obstaculos> itrO = obs.listIterator();
        while (itrO.hasNext()) {
            itrO.next().paint(g);
        }
    }

    public void nextLvl(){
        obs.clear();
        nivel++;
        switch(nivel % (new Random().nextInt(6))){
            case 1:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
                obs.add(new Obstaculos(2, 300, 400, 100, 100));
                obs.add(new Obstaculos(3, 500, 350, 100, 50));
                obs.add(new Obstaculos(4, 700, 200, 100, 50));
                break;
            case 2:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
                System.out.println("sdf");
                break;
            case 3:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
                break;
            case 4:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
                break;
            case 5:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
                break;
            case 6:
                obs.add(new Obstaculos(1, 0, 500, 1200, 100));
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
