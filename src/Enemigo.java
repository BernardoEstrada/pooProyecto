import java.awt.*;
import java.util.ArrayList;

public class Enemigo extends Persona {
    public Enemigo() {
        super();
    }

    public Enemigo(int posicionX, int posicionY) {
        super(posicionX, posicionY);
    }

    public Enemigo(int id, int danio, int posicionX, int posicionY, int tamanio, int tipo, int velocidadX, int velocidadY) {
        super(id, danio, posicionX, posicionY, tamanio, tipo, velocidadX, velocidadY);
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(posicionX, posicionY, tamanio, tamanio);
    }
}
