
import javax.swing.*;
import java.awt.*;



public class mainFrame extends JFrame{


    private projectPanel pp = new projectPanel();
    private Tabs T = new Tabs();


    private JPanel panel = new JPanel();

    public mainFrame(){
        super("CAVLiquefY");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000,800));
        setVisible(true);

        // Set the icon image
        //Image icon = Toolkit.getDefaultToolkit().getImage("path_to_your_image.png");
        //setIconImage(icon);


        add(pp, BorderLayout.NORTH);
        add(T, BorderLayout.CENTER);

    }

}
