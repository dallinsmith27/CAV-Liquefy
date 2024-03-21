import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class projectPanel extends JPanel {


    public static JTextField companyTextArea = new JTextField(15);
    public static JTextField engineerTextArea = new JTextField(15);
    public static JTextField ProjectTextArea = new JTextField(15);
    public static JTextField PeerReviewTextArea = new JTextField(15);
    public static JTextField DateTextArea = new JTextField(10);
    private static JLabel companyLabel = new JLabel("Company:");
    private static JLabel engineerLabel = new JLabel("Engineer:");
    private static JLabel ProjectLabel = new JLabel("Project Name:");
    private static JLabel PeerReviewLabel = new JLabel("Peer Review:");
    private static JLabel DateLabel = new JLabel("Date:");
    public static JTextField latText = new JTextField(10);
    private static JLabel lat = new JLabel("Latitude:");
    public static JTextField lonText = new JTextField(10);
    private JLabel lon = new JLabel("Longitude:");

    public projectPanel() {



//        companyTextArea
//        engineerTextArea
//        ProjectTextArea
//        PeerReviewTextArea
//        DateTextArea
        JPanel top = new JPanel();

        top.add(companyLabel);
        top.add(companyTextArea);

        top.add(ProjectLabel);
        top.add(ProjectTextArea);

        top.add(engineerLabel);
        top.add(engineerTextArea);

        top.add(PeerReviewLabel);
        top.add(PeerReviewTextArea);

        top.add(DateLabel);
        top.add(DateTextArea);

        top.add(DateLabel);
        top.add(DateTextArea);

        JPanel bottom = new JPanel();

        bottom.add(ProjectLabel);
        bottom.add(ProjectTextArea);

        bottom.add(lon);
        bottom.add(lonText);

        bottom.add(lat);
        bottom.add(latText);





        setLayout(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);
    }

}
