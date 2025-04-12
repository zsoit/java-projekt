package app.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ToolBarView {
    private ActionListener listener;
    private Icons myIcons = new Icons(); // Zakładając, że masz klasę Icons

    // Konstruktor klasy ToolBarView, który przyjmuje ActionListener
    public ToolBarView(ActionListener listener) {
        this.listener = listener;
    }

    // Klasa przycisku narzędziowego (dziedziczy po JButton)
    public static class CustomToolBarButton extends JButton {
        public CustomToolBarButton(String tooltip, Icon icon, ActionListener listener) {
            super("", icon); // Ikona zamiast tekstu
            setToolTipText(tooltip); // Ustawienie opisu
            addActionListener(listener); // Dodanie ActionListenera
        }
    }

    // Metoda do tworzenia paska narzędzi (ToolBar)
    public JToolBar createToolBar() {
        JToolBar jbt = new JToolBar(JToolBar.HORIZONTAL);
        jbt.setFloatable(false);

        // Tworzenie przycisków za pomocą CustomToolBarButton
        addButtonToToolBar(jbt, "Zapis pliku", myIcons.iconSave);
        addButtonToToolBar(jbt, "Drukowanie", myIcons.iconPrint);
        addButtonToToolBar(jbt, "Zamknięcie aplikacji", myIcons.iconExit);
        jbt.addSeparator();
        addButtonToToolBar(jbt, "Dodawanie", myIcons.iconSigma);
        addButtonToToolBar(jbt, "Średnia", myIcons.iconMean);
        addButtonToToolBar(jbt, "Minimum", myIcons.iconMin);
        addButtonToToolBar(jbt, "Maximum", myIcons.iconMax);
        jbt.addSeparator();
        addButtonToToolBar(jbt, "Kontekst pomocy", myIcons.iconHelp);
        addButtonToToolBar(jbt, "Informacje o autorze", myIcons.iconAbout);
        addButtonToToolBar(jbt, "Wykres kołowy", myIcons.iconPieChart);

        return jbt;
    }

    // Pomocnicza metoda do dodawania przycisków do paska narzędzi
    private void addButtonToToolBar(JToolBar jbt, String tooltip, Icon icon) {
        jbt.add(new CustomToolBarButton(tooltip, icon, listener));
    }
}