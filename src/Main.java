import javax.swing.*;

/**
 * @author Bernardo Estrada & Martin Noboa
 * matriculas:
 * @version final
 * @date May 8th,2019
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, UnsupportedLookAndFeelException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        VentanaMenu principal = new VentanaMenu(600, 1200);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
