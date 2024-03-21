import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.DoubleUnaryOperator;

public class Settlement extends JPanel {
    public static Calculations calc = new Calculations();
    private DefaultXYDataset dataset;
    private JFreeChart chart;
    JTextArea outputTextArea = new JTextArea();
    private static JButton button1 = new JButton("Generate Settlement Hazard Curves");
    private static JButton button2 = new JButton("Download Results");
    public static JCheckBox SadjcheckBox = new JCheckBox("Download Settlement Hazard Curve");

    public static JCheckBox CAVcheckBox = new JCheckBox("Download CAV Hazard Curve");

    public Settlement() {
        // Add components or customize the panel here
        setLayout(new BorderLayout());

        // Left panel
        JPanel leftPanel = new JPanel();
        JPanel leftUpPanel = new JPanel();
        JPanel leftDownPanel = new JPanel();


        TitledBorder SettlementBorder = BorderFactory.createTitledBorder("5.  Calculate Settlement");
        SettlementBorder.setTitleJustification(TitledBorder.CENTER);
        leftUpPanel.setBorder(SettlementBorder);
        leftUpPanel.setPreferredSize(new Dimension(300,60));

        TitledBorder SaveResultsBorder = BorderFactory.createTitledBorder("6.  Download Results");
        SaveResultsBorder.setTitleJustification(TitledBorder.CENTER);
        leftDownPanel.setBorder(SaveResultsBorder);
        leftDownPanel.setPreferredSize(new Dimension(300,120));

        //leftPanel.setBackground(Color.LIGHT_GRAY);
        button1.setPreferredSize(new Dimension(250,25));
        leftPanel.setPreferredSize(new Dimension(300, 300));
        leftUpPanel.add(button1);

        button2.setPreferredSize(new Dimension(250,25));
        leftDownPanel.add(button2);

        CAVcheckBox.setSelected(false);
        leftDownPanel.add(CAVcheckBox,LEFT_ALIGNMENT);

        SadjcheckBox.setSelected(true);
        leftDownPanel.add(SadjcheckBox,LEFT_ALIGNMENT);

        leftPanel.add(leftUpPanel);
        leftPanel.add(leftDownPanel);
        add(leftPanel, BorderLayout.WEST);

        // Right panel with JFreeChart (to be implemented)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        //rightPanel.setBackground(Color.green);
        //rightPanel.setPreferredSize(new Dimension(400, 300));
        add(rightPanel, BorderLayout.CENTER);

        //lower panel for output
        JPanel lowerPanel = new JPanel(new BorderLayout());


        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 150));

        lowerPanel.add(scrollPane, BorderLayout.CENTER);
        add(lowerPanel,BorderLayout.SOUTH);

        //graphPanel.setPreferredSize(new Dimension(500, 500));
        //create the dataset to be put in the graph and create the chart
        dataset = new DefaultXYDataset();
        chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));

        //graphPanel.add(chartPanel,BorderLayout.CENTER);

        rightPanel.add(chartPanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonsEnabled(false); // Disable both buttons
                // Create a new thread for the action
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("Button CLicked"+"\n");
                        try {
                            if (checkValues()){
                                calc.generateSettlementHaZardCurve(); // Sleep for 5 seconds
                                updateChart();
                            }else {
                                JOptionPane.showMessageDialog(null, "CAV Curve Data Not Found", "Settlement Hazard Curve Error", JOptionPane.ERROR_MESSAGE);
                            }


                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException caught: " + e.getMessage());
                            // Handle the null reference here
                        } catch (ArithmeticException e) {
                            System.out.println("ArithmeticException caught: " + e.getMessage());
                            // Handle the arithmetic error here
                        } catch (Exception e) {
                            System.out.println("Other exception caught: " + e.getMessage());
                            // Handle other types of exceptions here
                        }
                        // Perform the action for "Generate Settlement Hazard Curves" here
                        // Replace this with your specific functionality

                        setButtonsEnabled(true); // Enable both buttons after the process finishes
                    }
                });
                thread2.start(); // Start the thread
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonsEnabled(false); // Disable both buttons
                // Create a new thread for the action
                Thread thread5 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            int result = fileChooser.showSaveDialog(null);
                            if (result == JFileChooser.APPROVE_OPTION) {
                                String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
                                new Thread(() -> saveCSV(folderPath)).start();
                            }
                        } catch (Exception e) {
                            System.out.println("Other exception caught: " + e.getMessage());
                            // Handle other types of exceptions here
                        }
                        // Perform the action for "Generate Settlement Hazard Curves" here
                        // Replace this with your specific functionality

                        setButtonsEnabled(true); // Enable both buttons after the process finishes
                    }
                });
                thread5.start(); // Start the thread
            }
        });



        JPanel fillerPanel = new JPanel();
        //fillerPanel.setBackground(Color.WHITE);
        fillerPanel.setPreferredSize(new Dimension(300,1000));
        fillerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leftPanel.add(fillerPanel,BorderLayout.SOUTH);

    }

    private static void setButtonsEnabled(boolean enabled) {
        button1.setEnabled(enabled);
    }
    private JFreeChart createChart(DefaultXYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Settlement Hazard Curve",
                "Settlement (Log Scale)",
                "Rate of Exceedance (Log Scale)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        plot.setDomainAxis(new LogarithmicAxis("Settlement (Log Scale)"));
        plot.setRangeAxis(new LogarithmicAxis("Rate of Exceedance (Log Scale)"));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        return chart;
    }

    private boolean checkValues(){
        if (SeismicLoading.afes[0] == 0){
            return false;
        }
        return true;
    }
    public void updateChart(){
        dataset.removeSeries("Settlement");
        dataset.addSeries("Settlement", new double[][]{calc.edps.stream().mapToDouble(Double::doubleValue).toArray(), calc.afes.stream().mapToDouble(Double::doubleValue).toArray()});
    }

    private static void saveCSV(String filePath) {

        try {
            if (CAVcheckBox.isSelected()){
                FileWriter csvWriter = new FileWriter(filePath+"/CAV-Curve.csv");
                csvWriter.append(projectPanel.companyTextArea.getText()+","+projectPanel.engineerTextArea.getText()+","+projectPanel.PeerReviewTextArea.getText()+","+projectPanel.DateTextArea.getText()+", , , , , , , , , , , , , ,"+"\n");
                csvWriter.append("Project"+","+projectPanel.ProjectTextArea.getText()+"\n");
                csvWriter.append("Longitude"+","+projectPanel.lonText.getText()+"\n");
                csvWriter.append("Latitude"+","+projectPanel.latText.getText()+"\n");
                String imlString = "iml,";
                String afeString = "afe,";
                for(double iml :SeismicLoading.imls){
                    imlString = imlString+ Double.toString(iml)+",";
                }
                for(double afe : SeismicLoading.afes){
                    afeString = afeString + Double.toString(afe)+",";
                }
                imlString = imlString +"\n";
                afeString = afeString +"\n";
                csvWriter.append(imlString);
                csvWriter.append(afeString);
                csvWriter.flush();
                csvWriter.close();
            }
            if (SadjcheckBox.isSelected()) {
                FileWriter csvWriter = new FileWriter(filePath+"/Settlement-Curve.csv");
                csvWriter.append(projectPanel.companyTextArea.getText()+","+projectPanel.engineerTextArea.getText()+","+projectPanel.PeerReviewTextArea.getText()+","+projectPanel.DateTextArea.getText()+", , , , , , , , , , , , ,"+"\n");
                csvWriter.append("Project"+","+projectPanel.ProjectTextArea.getText()+"\n");
                csvWriter.append("Longitude"+","+projectPanel.lonText.getText()+"\n");
                csvWriter.append("Latitude"+","+projectPanel.latText.getText()+"\n");
                csvWriter.append("Mass of Structure"+","+StructureLoading.textFieldMst.getText()+"\n");
                csvWriter.append("Effective Height"+","+StructureLoading.textFieldHeff.getText()+"\n");
                csvWriter.append("Base"+","+StructureLoading.textFieldB.getText()+"\n");
                csvWriter.append("Length"+","+StructureLoading.textFieldL.getText()+"\n");
                csvWriter.append("Depth to Footing"+","+StructureLoading.textFieldDf.getText()+"\n");
                csvWriter.append("Bearing Pressure"+","+StructureLoading.textFieldq.getText()+"\n");
                String edpString = "edp,";
                String afeString = "afe,";
                for(double edp :calc.edps){
                    edpString = edpString+ Double.toString(edp)+",";
                }
                for(double afe : calc.afes){
                    afeString = afeString + Double.toString(afe)+",";
                }
                edpString = edpString +"\n";
                afeString = afeString +"\n";
                csvWriter.append(edpString);
                csvWriter.append(afeString);
                csvWriter.flush();
                csvWriter.close();
            }

            System.out.println("CSV has been saved.");
        } catch (IOException e) {
            System.err.println("Error in saving CSV: " + e.getMessage());
        }
    }
}