
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame  {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("Plik");
    private JMenu editMenu = new JMenu("Edycja");
    private JMenu viewMenu = new JMenu("Widok");
    private JMenu calculationsMenu = new JMenu("Obliczenia");
    private JMenu helpMenu = new JMenu("Pomoc");

    private JMenuItem exitFileItem = new JMenuItem("Wyjście");
    private JMenuItem saveFileItem = new JMenuItem("Zapisz");
    private JMenuItem openFileItem = new JMenuItem("Otwórz");

    private JMenuItem cutEditItem = new JMenuItem("Wytnij");
    private JMenuItem copyEditItem = new JMenuItem("Kopiuj");
    private JMenuItem pasteEditItem = new JMenuItem("Wklej");

    private JMenuItem calculationsSum = new JMenuItem("Suma");

    private JMenuItem calculationsAvg = new JMenuItem("Średnia");

    private JMenuItem calculationsMin = new JMenuItem("Min");

    private JMenuItem calculationsMax = new JMenuItem("Max");

    private JMenuItem helpAboutApp = new JMenuItem("O programie");
    private JMenuItem helpManual = new JMenuItem("Panel pomocy");


    public JMenuBar createMenu(){
        // Menu

        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(exitFileItem);
        editMenu.add(cutEditItem);
        editMenu.add(copyEditItem);
        editMenu.add(pasteEditItem);


        calculationsMenu.add(calculationsSum);
        calculationsMenu.add(calculationsAvg);
        calculationsMenu.add(calculationsMin);
        calculationsMenu.add(calculationsMax);

        helpMenu.add(helpManual);
        helpMenu.add(helpAboutApp);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(calculationsMenu);
        menuBar.add(helpMenu);

        return menuBar;

    }

    public void Events(){


        exitFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpAboutApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.showAboutDialog();
            }
        });
    }


    public void showAboutDialog() {
        JDialog aboutDialog = new JDialog(this, "O programie", true);
        aboutDialog.setSize(300, 200);
        aboutDialog.setLocationRelativeTo(this);
        aboutDialog.setLayout(new BorderLayout());

        // Treść informacji o programie i autorze
        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setText("Autor: Jakub Achtelik U-20019\nWersja: 1.0\n\nPK - Zadanie 1");
        aboutDialog.add(aboutTextArea, BorderLayout.CENTER);

        // Przycisk do zamknięcia okna
        JButton closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> aboutDialog.dispose());
        aboutDialog.add(closeButton, BorderLayout.SOUTH);

        // Wyświetlenie okna dialogowego
        aboutDialog.setVisible(true);
    }
}
