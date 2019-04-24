import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FondoMenu extends Canvas{
    private String letrero;



    public FondoMenu(String letrero){
        super();
        this.letrero=letrero;
    }




    public void paint(Graphics g){
        Font fuente= new Font("SANS_SERIF",Font.PLAIN,72);
        g.setFont(fuente);
        g.setColor(Color.WHITE);
        this.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        g.drawString(letrero, 230, 100);

    }



}
