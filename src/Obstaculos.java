import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Obstaculos {
    private int id, posicionX, posicionY, tamanioX, tamanioY;

    public Obstaculos(int id, int posicionX, int posicionY, int tamanioX, int tamanioY) {
        this.id = id;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tamanioX = tamanioX;
        this.tamanioY = tamanioY;
    }

    public void paint(Graphics g) {
        //Random r = new Random();
        //g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        g.setColor(Color.BLACK);
        g.fillRect(posicionX, posicionY, tamanioX, tamanioY);
    }

    @Override
    public boolean equals(Object o) {
        Obstaculos that = (Obstaculos) o;
        if ((that.posicionY + tamanioY == this.posicionY && that.posicionX <= this.posicionX + tamanioX) || (that.posicionY + tamanioY == this.posicionY && that.posicionX > this.posicionX)) {
            return true;
        } else return false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getTamanioX() {
        return tamanioX;
    }

    public void setTamanioX(int tamanioX) {
        this.tamanioX = tamanioX;
    }

    public int getTamanioY() {
        return tamanioY;
    }

    public void setTamanioY(int tamanioY) {
        this.tamanioY = tamanioY;
    }
}
