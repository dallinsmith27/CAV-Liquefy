import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ErrorMessage {
    private JFrame frame;
    private JLabel messageLabel;

    public ErrorMessage(String message) {
        frame = new JFrame("Error Message");
        messageLabel = new JLabel(message);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titleBar.add(okButton);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleBar, BorderLayout.NORTH);
        panel.add(messageLabel, BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}



// Other option
//import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.*;
//
//public class ErrorMessage {
//    private JFrame frame;
//    private JLabel messageLabel;
//
//    public ErrorMessage(String message) {
//        frame = new JFrame("Error Message");
//        messageLabel = new JLabel(message);
//        JButton okButton = new JButton("OK");
//        okButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//            }
//        });
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(messageLabel, BorderLayout.CENTER);
//        panel.add(okButton, BorderLayout.SOUTH);
//        frame.add(panel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}

