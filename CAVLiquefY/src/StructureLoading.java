import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StructureLoading extends JPanel {
    public static soilInfo sI = new soilInfo();

    public static JTextField textFieldMst = new JTextField("1861860");
    public static JTextField textFieldHeff = new JTextField("16");
    public static JTextField textFieldq = new JTextField("91.3");
    public static JTextField textFieldB = new JTextField("10");
    public static JTextField textFieldDf = new JTextField("2.0");
    public static JTextField textFieldL = new JTextField("20");

    public StructureLoading() {
        // Set layout for the main panel
        setLayout(new BorderLayout(5,5));

        // Create left and right sub-panels
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftUpPanel = new JPanel();
        JPanel leftDownPanel = new JPanel();

        // Set layouts for sub-panels

        leftPanel.setLayout(new FlowLayout());
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setPreferredSize(new Dimension(300,300));

        rightPanel.setLayout(new BorderLayout(5,5));
        //leftUpPanel.setLayout(new BorderLayout(5,5));
        //leftDownPanel.setLayout(new BorderLayout(5,5));

        leftDownPanel.setPreferredSize(new Dimension(300,210));
        leftUpPanel.setPreferredSize(new Dimension(300,90));

        // Create titled borders with centered titles
        TitledBorder StructureBorder = BorderFactory.createTitledBorder("2.  Structure Interactions");
        StructureBorder.setTitleJustification(TitledBorder.CENTER);
        leftUpPanel.setBorder(StructureBorder);


        TitledBorder FoundationBorder = BorderFactory.createTitledBorder("3.  Foundation Interactions");
        FoundationBorder.setTitleJustification(TitledBorder.CENTER);
        leftDownPanel.setBorder(FoundationBorder);

        JLabel labelMst = new JLabel("Mass of Structure (kg):");

        textFieldMst.setPreferredSize(new Dimension(70,20));
        // Set preferred width

        JLabel labelHeff = new JLabel("Effective Height (m):");

        textFieldHeff.setPreferredSize(new Dimension(70,20));
        // Set preferred width

        leftUpPanel.add(labelMst);
        leftUpPanel.add(textFieldMst);
        leftUpPanel.add(labelHeff);
        leftUpPanel.add(textFieldHeff);

        // Add sub-panels to the main panel
        leftPanel.add(leftUpPanel,BorderLayout.NORTH);

        JPanel verticle = new JPanel();
        verticle.setLayout(new BoxLayout(verticle, BoxLayout.Y_AXIS));
        verticle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelq = new JPanel();
        JLabel labelq = new JLabel("Bearing Pressure (kilopascals):");

        textFieldq.setPreferredSize(new Dimension(70,20));
        panelq.add(labelq);
        panelq.add(textFieldq);

        JPanel panelB = new JPanel();
        JLabel labelB = new JLabel("Footing Base (m):");

        textFieldB.setPreferredSize(new Dimension(70,20));
        panelB.add(labelB);
        panelB.add(textFieldB);

        JPanel panelL = new JPanel();
        JLabel labelL = new JLabel("Footing Length (m):");

        textFieldL.setPreferredSize(new Dimension(70,20));
        panelL.add(labelL);
        panelL.add(textFieldL);

        JPanel panelDf = new JPanel();
        JLabel labelDf = new JLabel("Depth to Footing (m):");

        textFieldDf.setPreferredSize(new Dimension(70,20));
        panelDf.add(labelDf);
        panelDf.add(textFieldDf);


        verticle.add(panelB);
        verticle.add(panelL);
        verticle.add(panelDf);

        verticle.add(panelq);

        leftDownPanel.add(verticle,BorderLayout.NORTH);

        leftPanel.add(leftDownPanel,BorderLayout.CENTER);
        add(leftPanel,BorderLayout.WEST);

        TitledBorder SoilBorder = BorderFactory.createTitledBorder("4.  Soil Profile");
        SoilBorder.setTitleJustification(TitledBorder.CENTER);
        rightPanel.setBorder(SoilBorder);
        rightPanel.add(sI,BorderLayout.CENTER);
        add(rightPanel);

        JPanel fillerPanel = new JPanel();
        //fillerPanel.setBackground(Color.WHITE);
        fillerPanel.setPreferredSize(new Dimension(300,1000));
        fillerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leftPanel.add(fillerPanel,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Structure Loading Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new StructureLoading());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
