import java.awt.*;

public class Jugador extends Persona {
    private String nombre;

    public Jugador(int id, int danio, int posicionX, int posicionY, int tamanio, int tipo, int velocidadX, int velocidadY, String nombre) {
        super(id, danio, posicionX, posicionY, tamanio, tipo, velocidadX, velocidadY);
        this.nombre = nombre;
    }

    public Jugador(int id, int posicionX, int posicionY, int tamanio, String nombre) {
        super(id, posicionX, posicionY, tamanio);
        this.nombre = nombre;
    }

    @Override
    public void die() {

    }

    public void paint(Graphics g) {
        if (facingF) {
            g.setColor(Color.BLUE);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX + tamanio / 2, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(posicionX, posicionY, tamanio, tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX, posicionY + tamanio / 2, tamanio / 2, tamanio / 10);
        }
        bala.paint(g);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
