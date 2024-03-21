import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class soilInfo extends JPanel implements ActionListener {

    private JComboBox<String> typeComboBox;
    private JButton addRowButton;
    private JButton removeRowButton;
    public static JTable table;
    private DefaultTableModel tableModel;

    public static JCheckBox lowPermCheckbox = new JCheckBox("Low permeability Layer Above Uppermost Susceptible Layer? (Flpc)");
    public soilInfo() {
        // Set up the UI components

        JLabel typeLabel = new JLabel("Test Type:");
        String[] typeOptions = {"SPT","CPT"};
        typeComboBox = new JComboBox<>(typeOptions);
        typeComboBox.addActionListener(this);
        addRowButton = new JButton("Add Row");
        addRowButton.addActionListener(this);
        removeRowButton = new JButton("Remove Row");
        removeRowButton.addActionListener(this);
        Object[] columnHeaders = {"Depth","Width", "N160 (SPT)","Susceptible?", "Description"};
        Object[][] data = {{2.5,5,10,true, "Example Layer"}};
        tableModel = new DefaultTableModel(data, columnHeaders);
        table = new JTable(tableModel);
        soilPicker sp = new soilPicker("Input Soil Profile",table);





        JPanel permeabilityPanel = new JPanel();

        permeabilityPanel.add(lowPermCheckbox);


        // Add the components to the panel
        JPanel inputPanel = new JPanel();

        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(addRowButton);
        btnPanel.add(removeRowButton);
        btnPanel.add(sp);


        JPanel layerPanel = new JPanel();
        layerPanel.add(new JScrollPane(table));



        JPanel verticle = new JPanel();
        verticle.setLayout(new BoxLayout(verticle, BoxLayout.Y_AXIS));
        verticle.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticle.add(inputPanel);
        verticle.add(permeabilityPanel);
        verticle.add(layerPanel);
        verticle.add(btnPanel);

        setLayout(new BorderLayout());
        add(verticle, BorderLayout.NORTH);
        //add(layerPanel, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == typeComboBox) {
            // Update the column header based on the selected test type
            String selectedType = (String) typeComboBox.getSelectedItem();
            String columnTitle = selectedType.equals("CPT") ? "Qc1n (CPT)" : "N160 (SPT)";
            tableModel.setColumnIdentifiers(new Object[]{"Depth","Width", columnTitle,"Susceptible?", "Description"});
        } else if (e.getSource() == addRowButton) {
            // Add a new row to the table
            tableModel.addRow(new Object[]{null, null, null});
        } else if (e.getSource() == removeRowButton) {
            // Remove the selected row from the table
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
            else {
                new ErrorMessage("Please select a row to Remove");
            }

        }
    }
    public boolean iscpt(){
        String selectedType = (String) typeComboBox.getSelectedItem();
        return selectedType.equals("CPT") ? true : false;
    }
}
