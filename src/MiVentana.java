import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MiVentana extends JFrame{

    public MiVentana() {
        super();
        setTitle("Proyecto Final");
        setSize(500,500);
        setLocationRelativeTo(null);
        MiCanvas canvas= new MiCanvas();
        setLayout(new BorderLayout());
        add(canvas,BorderLayout.CENTER);


    }

    public MiVentana(int height, int width) {
        super();
        setTitle("Proyecto Final");
        setSize(width,height);
        setLocationRelativeTo(null);
        MiCanvas canvas= new MiCanvas();
        setLayout(new BorderLayout());
        add(canvas,BorderLayout.CENTER);


    }

}