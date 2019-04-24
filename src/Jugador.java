import java.awt.*;
import java.util.ArrayList;

public class Jugador extends Persona {
    private String nombre;

    public Jugador( int id, int danio, int posicionX, int posicionY, int tamanio, int tipo, int velocidadX, int velocidadY, String nombre) {
        super( id, danio, posicionX, posicionY, tamanio, tipo, velocidadX, velocidadY);
        this.nombre = nombre;
    }

    public Jugador( int id, int posicionX, int posicionY, int tamanio,String nombre) {
        super( id, posicionX, posicionY, tamanio);
        this.nombre = nombre;
        bala = new Proyectil(10,this.getPosicionX(),this.getPosicionY());
    }

    public void paint(Graphics g){
        if(facingF){
            g.setColor(Color.BLUE);
            g.fillRect(posicionX,posicionY,tamanio,tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX+tamanio/2,posicionY+tamanio/2,tamanio/2,tamanio/10);
        } else{
            g.setColor(Color.BLUE);
            g.fillRect(posicionX,posicionY,tamanio,tamanio);
            g.setColor(Color.RED);
            g.fillRect(posicionX,posicionY+tamanio/2,tamanio/2,tamanio/10);
        }
        bala.paint(g);
    }


    public void mover(int tecla, ArrayList<Obstaculos> obs){
        /*
        37 izquierda
		38 arriba
		39 derecha
		40 abajo
         */
        //Rectangle u = new Rectangle(posicionX,posicionY,tamanio,tamanio);
        if(tecla == 37){
            facingF = false;
            if(!colisionLeft(obs)){
                posicionX -= velocidadX;
            }
        }
        if(tecla == 39){
            facingF = true;
            if(!colisionRight(obs)){
               posicionX += velocidadX;
           }
        }
        if(tecla == 38 && !jumping){
            jumping=true;
            velocidadY=-7;
        }

    }

    public boolean disparar(){
        if(!bala.isActive()){
            bala.setDireccion(facingF);
            bala.setPosicionX(posicionX+tamanio/2);
            bala.setPosicionY(posicionY+tamanio/2);
            bala.setActive(true);
            return true;
        }
        return false;
    }

    public Proyectil getBala() {
        return bala;
    }

    public void setBala(Proyectil bala) {
        this.bala = bala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
