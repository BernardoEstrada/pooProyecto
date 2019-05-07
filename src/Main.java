import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, UnsupportedLookAndFeelException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        TestVen principal = new TestVen();
        principal.setSize(500, 500);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
}
