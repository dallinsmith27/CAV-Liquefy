import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class soilPicker extends JButton implements ActionListener {
    private JTable table;

    public soilPicker(String label, JTable table) {
        super(label);
        this.table = table;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a file chooser dialog and set its file filter to .csv files
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
        fileChooser.setFileFilter(csvFilter);

        // Show the file chooser dialog and wait for the user to select a file
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Read the contents of the selected file into a List<String[]>
            List<String[]> fileContents = new ArrayList<>();
            String filePath = fileChooser.getSelectedFile().getPath();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] row = line.split(",");
                    fileContents.add(row);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Fill the table with the file contents
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            for (String[] row : fileContents) {
                Object[] rowData = {row[0], row[1], row[2], row[3]};
                model.addRow(rowData);
            }
        }
    }
}
