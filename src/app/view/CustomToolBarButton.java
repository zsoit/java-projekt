package app.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CustomToolBarButton extends JButton {

    public CustomToolBarButton(String tooltip, Icon icon, ActionListener listener) {
        super("", icon); // Ikona zamiast tekstu
        setToolTipText(tooltip); // Ustawienie opisu
        addActionListener(listener); // Dodanie ActionListenera
    }
}