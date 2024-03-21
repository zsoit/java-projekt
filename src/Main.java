import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Box Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Ustawianie układu w pionie

        JPanel panel1 = new JPanel(); // Panel 1
        panel1.add(new JButton("Button 1"));
        panel1.add(new JButton("Button 2"));
        panel1.add(new JButton("Button 3"));

        JPanel panel2 = new JPanel(); // Panel 2
        panel2.add(new JButton("Button 4"));
        panel2.add(new JButton("Button 5"));

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
