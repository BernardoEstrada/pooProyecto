import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaMenu extends JFrame implements ActionListener {
    private JPanel panelS;
    private JButton iniciar,infoButton,helpButton, exitButton;
    private int height, width;
    private Image bg;

    private boolean help;


    public VentanaMenu(int height, int width) {
        super("Proyecto Final ");
        setSize(width, height);
        setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        panelS = new JPanel();
        iniciar = new JButton("Start");
        iniciar.setFont(new Font("Helvetica", Font.BOLD,18));
        iniciar.setBorderPainted(false);

        iniciar.addActionListener(this);

        helpButton = new JButton("Help");
        helpButton.setFont(new Font("Helvetica", Font.BOLD,18));
        helpButton.setBorderPainted(false);
        helpButton.addActionListener(this);
        helpButton.setPreferredSize(new Dimension(150, 60));

        iniciar.setPreferredSize(new Dimension(150, 60));
        try {
            bg = ImageIO.read(new File("images/bg1.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }

        panelS.setLayout(new FlowLayout());
        panelS.setOpaque(true);
        panelS.setBackground(Color.WHITE);
        panelS.setForeground(Color.MAGENTA);
        panelS.add(iniciar);
        panelS.add(helpButton);



        this.height = height;
        this.width = width;

        this.add(panelS, BorderLayout.SOUTH);
    }

    public void paint(Graphics g){

        g.drawImage(bg, 0, 0,1200 ,600,this);
        if (help){
            g.fillRect(0,0,1200,600);
        }
    }



    @Override
    public void actionPerformed(ActionEvent evento) {
        // TODO Auto-generated method stub
        if (evento.getSource() == iniciar) {
            MiVentana mv = new MiVentana(height, width);
            mv.setVisible(true);
            mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(false);
        } else if(evento.getSource() == helpButton){
            VentanaAyuda va = new VentanaAyuda(height,width);
            va.setVisible(true);
            va.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(false);
        }else {
            System.exit(0);

        }
    }

}
