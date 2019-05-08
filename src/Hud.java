import java.awt.*;

public class Hud {
    public Hud() {
    }

    public static void paint(Graphics g, int vida, int puntos) {
        g.setColor(Color.RED);
        for (int i = 0; i < vida; i++) {
            g.fillOval(10 + (i * 30), 10, 20, 20);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g.drawString("Level: " + puntos, 10, 50);
    }
}
