package app.view;




import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {

    public CustomButton(String text, Icon icon, ActionListener listener) {
        super(text, icon);
        setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Ustawienie czcionki
        addActionListener(listener); // Dodanie ActionListenera
    }

}