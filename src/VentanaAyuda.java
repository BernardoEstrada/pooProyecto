import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VentanaAyuda extends JFrame implements ActionListener {

        private JPanel panelS;
        private JButton home;
        private int height, width;
        private Image bg;




        public VentanaAyuda(int height, int width) {
            super("Proyecto Final ");
            setSize(width, height);
            setLocationRelativeTo(null);

            this.setLayout(new BorderLayout());
            panelS = new JPanel();
            home = new JButton("Home");
            home.setFont(new Font("Helvetica", Font.BOLD,18));
            home.setBorderPainted(false);

            home.addActionListener(this);

            home.setPreferredSize(new Dimension(150, 60));
            try {
                bg = ImageIO.read(new File("images/inst.jpg"));
            }catch(IOException e){
                e.printStackTrace();
            }

            panelS.setLayout(new FlowLayout());
            panelS.setOpaque(true);
            panelS.setBackground(Color.WHITE);
            panelS.setForeground(Color.MAGENTA);
            panelS.add(home);



            this.height = height;
            this.width = width;

            this.add(panelS, BorderLayout.SOUTH);
        }

        public void paint(Graphics g){

            g.drawImage(bg, 0, 0,1200 ,600,this);

        }



        @Override
        public void actionPerformed(ActionEvent evento) {
            // TODO Auto-generated method stub
            if (evento.getSource() == home) {
                VentanaMenu principal = new VentanaMenu(600, 1200);
                principal.setVisible(true);
                principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.setVisible(false);
            }else {
                System.exit(0);

            }
        }

    }


