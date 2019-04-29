import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        VentanaMenu principal = new VentanaMenu(600, 1200);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
}
