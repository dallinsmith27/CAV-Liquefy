import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Home extends JPanel {


    public Home() {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Welcome to CAV LiquefY");
        title.setAlignmentX(CENTER_ALIGNMENT);
        Font originalFont = title.getFont();
        Font newFont = originalFont.deriveFont(20.0f);
        title.setFont(newFont);
        titlePanel.add(title);
        add (titlePanel);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());


        JLabel step1 = new JLabel("Step 1:  Go to the Seismic Loading tab. Input project name, latitude, and longitude. (we recommend filling out all info in the top panel) \n Then upload a CAV hazard curve or calculate the CAV hazard curve.");
        add(step1);
        add(Box.createVerticalGlue());
        JLabel step2 = new JLabel("Step 2:  Go to the Site Loading tab. Upload all Structure specific parameters.");
        add(step2);
        add(Box.createVerticalGlue());
        JLabel step3 = new JLabel("Step 3:  Upload all Foundation specific parameters.");
        add(step3);
        add(Box.createVerticalGlue());
        JLabel step4 = new JLabel("Step 4:  Upload the soil profile into the table.");
        add(step4);
        add(Box.createVerticalGlue());
        JLabel step5 = new JLabel("Step 5:  Go to the Settlement tab. Calculate the settlement hazard curve.");
        add(step5);
        add(Box.createVerticalGlue());
        JLabel step6 = new JLabel("Step 6:  Select which curves you would like to download. Download the files.");
        add(step6);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());

    }

    public static void main(String[] args) {
        // Create and display the Home panel in a JFrame
        JFrame frame = new JFrame("Home");
        Home homePanel = new Home();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(homePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
