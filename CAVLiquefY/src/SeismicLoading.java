import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class SeismicLoading extends JPanel {

    public static double imls[] = new double[]{1.628318726, 2.651421872, 4.317359884, 7.030037944, 11.44714243, 18.63959637, 30.3512038, 49.42143349, 80.4738456, 131.0370697, 213.3701144, 347.4345527, 565.7341881, 921.1955721, 1500.0};

    public static double afes[] = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private JButton runButton;
    private JButton saveButton;
    private JButton importButton;
    private JComboBox<String> ModelType = new JComboBox<>(new String[]{"2018 Nshm-Conus","2021 Nshm-Hawaii","2023 Nshm-Alaska"});
    private JTextArea dataField1;
    private JTextArea dataField2;
    private JTextArea dataField3;
    static JTextArea outputTextArea = new JTextArea();
    private JFreeChart chart;
    private DefaultXYDataset dataset;
    private boolean isProcessRunning = false;
    JButton openButton = new JButton("   Import Hazard Curve   ");

    public SeismicLoading() {
        setLayout(new BorderLayout());



        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 300));

        ModelType.setSize(new Dimension(250,25));
        //ModelType.setPreferredSize(new Dimension(250,25));
        //ModelType.setAlignmentY(Component.TOP_ALIGNMENT);
        ModelType.setAlignmentX(Component.CENTER_ALIGNMENT);

        runButton = new JButton("        Calculate Hazard Curve        ");
        runButton.setPreferredSize(new Dimension(250,25));
        //runButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (!isProcessRunning && checkDouble(projectPanel.latText.getText()) && checkDouble(projectPanel.lonText.getText()) && checkString(projectPanel.ProjectTextArea.getText())) {
                    isProcessRunning = true;
                    runButton.setText("Calculating...        ");
                    runButton.setEnabled(false);
                    openButton.setEnabled(false);

                    Thread taskThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\nshmp-haz-site.csv", false))) {
                                // Write CSV header
                                writer.write("name,lat,lon\n");
                                // Add data to the CSV columns

                                writer.write(projectPanel.ProjectTextArea.getText() + "," + projectPanel.latText.getText() + "," + projectPanel.lonText.getText() + "\n");
                                writer.close();

                                System.out.println("site CSV file created and saved successfully.");
                                executeCommand();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.err.println("Error creating or saving CSV file.");
                            }



                            isProcessRunning = false;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    runButton.setText("Calculate Hazard Curve");
                                    runButton.setEnabled(true);
                                    openButton.setEnabled(true);
                                }
                            });
                        }
                    });
                    taskThread.start();
                }else {
                    updateOutputFromFile("ERROR: The input data is not in a valid format or was not entered \nProject Name: " +
                            projectPanel.ProjectTextArea.getText() + "\nLatitude: " + projectPanel.latText.getText() + "\nLongitude: " + projectPanel.lonText.getText()+
                            "\nEnsure the correct data was entered and try again");
                    JOptionPane.showMessageDialog(SeismicLoading.this,
                            "ERROR: The input data is not in a valid format \nProject Name: " +
                                    projectPanel.ProjectTextArea.getText() + "\nLatitude: " + projectPanel.latText.getText() + "\nLongitude: " + projectPanel.lonText.getText()+
                                    "\nEnsure the correct data was entered and try again",
                            "Check Inputs", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        openButton.setPreferredSize(new Dimension(250,25));
        //openButton.setAlignmentX(CENTER_ALIGNMENT);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isProcessRunning) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(SeismicLoading.this);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        if (filePath.endsWith(".csv")) {
                            try {
                                Reader csvreader = new FileReader(filePath);
                                CSVParser csvParser = new CSVParser(csvreader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                                String[] columnNames = csvParser.getHeaderMap().keySet().toArray(new String[0]);
                                CSVRecord record = csvParser.getRecords().get(0);
                                afes = new double[columnNames.length-3];
                                imls = new double[columnNames.length-3];
                                int i = 0;
                                for (String columnName : columnNames) {

                                    if (columnName.equals("name")) {
                                        projectPanel.ProjectTextArea.setText(record.get(String.valueOf(columnName)));
                                    }
                                    else if (columnName.equals("lat")) {
                                        projectPanel.latText.setText(record.get(String.valueOf(columnName)));
                                    }
                                    else if (columnName.equals("lon")) {
                                        projectPanel.lonText.setText(record.get(String.valueOf(columnName)));
                                    }
                                    else {
                                        afes[i] = Double.valueOf(record.get(String.valueOf(columnName)));
                                        imls[i] = Double.valueOf(String.valueOf(columnName));
                                        ++i;
                                    }

                                }

                                updateChart();
                                String imlString = "";
                                String afeString = "";
                                for (var z = 0; z < imls.length; z++) {
                                    imlString = imlString+String.valueOf(imls[z])+",";
                                    afeString = afeString+String.valueOf(afes[z])+",";
                                }
                                updateOutputFromFile("Loaded Custom Hazard Curve: \nIMLS: "+imlString+"\nAFES: "+afeString);


                            } catch (IOException ie) {
                                JOptionPane.showMessageDialog(SeismicLoading.this,
                                        "Could not Load: " + filePath,
                                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(SeismicLoading.this,
                                    "Please select a CSV file.",
                                    "Invalid File", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });


        JPanel lowerPanel = new JPanel(new BorderLayout());


        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 150));

        lowerPanel.add(scrollPane, BorderLayout.CENTER);



        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        //graphPanel.setPreferredSize(new Dimension(500, 500));
        //create the dataset to be put in the graph and create the chart
        dataset = new DefaultXYDataset();
        chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(graphPanel.getHeight(), graphPanel.getHeight()));
        graphPanel.add(chartPanel,BorderLayout.CENTER);

        JPanel CAVButtonPanel = new JPanel();
        CAVButtonPanel.setLayout(new BorderLayout(5,5));
        CAVButtonPanel.add(openButton,BorderLayout.NORTH);
        CAVButtonPanel.add(runButton,BorderLayout.CENTER);
        TitledBorder CAVTitle = BorderFactory.createTitledBorder("1.  Generate CAV Hazard Curve");
        CAVTitle.setTitleJustification(TitledBorder.CENTER);
        CAVButtonPanel.setBorder(CAVTitle);
        leftPanel.add(CAVButtonPanel);

        JPanel nshmpSettingsPanel = new JPanel();
        nshmpSettingsPanel.setLayout(new BorderLayout(5,5));
        nshmpSettingsPanel.add(ModelType);
        TitledBorder nshmpSettingsBorder = BorderFactory.createTitledBorder("CAV Calculation Settings");
        nshmpSettingsBorder.setTitleJustification(TitledBorder.CENTER);
        nshmpSettingsPanel.setBorder(nshmpSettingsBorder);
        leftPanel.add(nshmpSettingsPanel);

        JPanel fillerPanel = new JPanel();
       //fillerPanel.setBackground(Color.WHITE);
        fillerPanel.setPreferredSize(new Dimension(300,1000));
        fillerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        leftPanel.add(fillerPanel);

        add(leftPanel, BorderLayout.WEST);
        add(lowerPanel,BorderLayout.SOUTH);
        add(graphPanel, BorderLayout.CENTER);
    }

    public static void updateOutputFromFile(String output) {
            outputTextArea.setText(output);
    }

    private boolean checkDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean checkString(String text){
        return !text.trim().isEmpty();
    }

    private void executeCommand() {
        String outputString = "";



        String WD = System.getProperty("user.dir");
        deleteFolder(WD +"\\HazardCurve");

        String jar = WD +"\\nshmp-haz.jar ";
        String nshmp = "gov.usgs.earthquake.nshmp.HazardCalc";
        String model = WD +"\\nshm-conus";
        if (ModelType.getSelectedItem() == "2021 Nshm-Hawaii") {
            model = WD +"\\nshm-hawaii-main";
        }else if (ModelType.getSelectedItem() == "2023 Nshm-Alaska") {
            model = WD +"\\nshm-alaska-main";
        }

        String site = WD +"\\nshmp-haz-site.csv";
        createConfig(WD);



        String configJson = WD +"\\calc-config.json";
        try {
            // Execute the ping command
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", jar, nshmp, model, site, configJson);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                outputString = outputString + "\n" + line;
                updateOutputFromFile(outputString);
                System.out.println(line);
            }


            // Wait for the process to complete
            int exitCode = process.waitFor();





        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        getAfes();
        updateChart();

        outputString = outputString + "\n" + "FINISHED!!";
        updateOutputFromFile(outputString);

    }

    public void updateChart(){

        dataset.removeSeries("CAV");
        if (afes[0] != 0) {
            dataset.addSeries("CAV", new double[][]{imls, afes});
        }
    }

    private void createConfig(String fp){
        fp = fp.replace("\\","\\\\");
        try {
            // Your JSON content
            String jsonContent = "{\n" +
                    "  \"hazard\": {\n" +
                    "    \"imts\": [\"CAV\"]\n" +
                    "  },\n" +
                    "  \"output\": {\n" +
                    "    \"directory\": \""+fp+"\\\\HazardCurve\"\n" +
                    "  }\n" +
                    "}";

            FileWriter writer = new FileWriter(fp+"\\calc-config.json");
            writer.write(jsonContent);
            writer.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving config file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private JFreeChart createChart(DefaultXYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Cumulative Absolute Velocity Hazard Curve",
                "CAV (Log Scale)",
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

        plot.setDomainAxis(new LogarithmicAxis("CAV (Log Scale)"));
        plot.setRangeAxis(new LogarithmicAxis("Rate of Exceedance (Log Scale)"));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        return chart;
    }
    public static void getAfes(){
        try {
            Reader csvreader = new FileReader(System.getProperty("user.dir")+"\\HazardCurve\\CAV\\curves.csv");

            CSVParser csvParser = new CSVParser(csvreader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {

                for (var i = 0; i < imls.length; i++) {
                    afes[i] = Double.valueOf(record.get(String.valueOf(imls[i])));
                }

                String name = record.get("name");
                String lon = record.get("lon");
                String lat = record.get("lat");

                // Process the data as needed
                System.out.println("Name: " + name + ", Lon: " + lon + ", Lat: " + lat);
            }

            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            System.out.println("Folder does not exist.");
        } else {
            try {
                delete(folder);
                System.out.println("Folder has been deleted.");
            } catch (Exception e) {
                System.err.println("Failed to delete the folder: " + e.getMessage());
            }
        }
    }

    public static void delete(File file) throws Exception {
        if (file.isDirectory()) {
            // Recursively delete contents
            for (File f : file.listFiles()) {
                delete(f);
            }
        }
        if (!file.delete()) {
            throw new Exception("Failed to delete file: " + file);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Seismic Loading");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new SeismicLoading());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

