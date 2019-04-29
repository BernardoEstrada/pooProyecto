import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaMenu extends JFrame implements ActionListener {
    private JPanel panelS;
    private JButton iniciar;
    private Canvas fondo;
    private int height, width;


    public VentanaMenu(int height, int width) {
        super("Proyecto Final ");
        setSize(width, height);
        setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        panelS = new JPanel();
        iniciar = new JButton("Start");

        iniciar.addActionListener(this);


        iniciar.setPreferredSize(new Dimension(150, 60));


        panelS.setLayout(new FlowLayout());
        panelS.setOpaque(true);
        panelS.setBackground(Color.WHITE);
        panelS.setForeground(Color.MAGENTA);
        panelS.add(iniciar);

        fondo = new FondoMenu("Por Gloria");

        this.height = height;
        this.width = width;

        this.add(fondo, BorderLayout.CENTER);
        this.add(panelS, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // TODO Auto-generated method stub
        if (evento.getSource() == iniciar) {
            MiVentana mv = new MiVentana(height, width);
            mv.setVisible(true);
            mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(false);
        } else {
            System.exit(0);

        }
    }

}
