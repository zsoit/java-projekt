
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame  {
    private final String AUTHOR = "Jakub Achtelik U-20019";
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu,calculationsMenu, helpMenu;
    private JMenuItem exitFileItem, saveFileItem, openFileItem;
    private JMenuItem cutEditItem, copyEditItem, pasteEditItem;
    private JMenuItem calculationsSum, calculationsAvg, calculationsMin, calculationsMax;
    private  JMenuItem helpAboutApp, helpManual;
    private JDialog aboutDialog;
    private JTextArea aboutTextArea;
    private JButton closeButton;

    public Menu(){

        menuBar = new JMenuBar();

        fileMenu = new JMenu("Plik");
        editMenu = new JMenu("Edycja");
        viewMenu = new JMenu("Widok");
        calculationsMenu = new JMenu("Obliczenia");
        helpMenu = new JMenu("Pomoc");

        exitFileItem = new JMenuItem("Wyjście");
        saveFileItem = new JMenuItem("Zapisz");
        openFileItem = new JMenuItem("Otwórz");

        cutEditItem = new JMenuItem("Wytnij");
        copyEditItem = new JMenuItem("Kopiuj");
        pasteEditItem = new JMenuItem("Wklej");

        calculationsSum = new JMenuItem("Suma");
        calculationsAvg = new JMenuItem("Średnia");
        calculationsMin = new JMenuItem("Min");
        calculationsMax = new JMenuItem("Max");

        helpAboutApp = new JMenuItem("O programie");
        helpManual = new JMenuItem("Panel pomocy");

        this.Events();

    }


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


        helpAboutApp.addActionListener(e -> new Menu().showAboutDialog());
    }


    public void showAboutDialog() {
        aboutDialog = new JDialog(this, "O programie", true);
        aboutDialog.setSize(300, 200);
        aboutDialog.setLocationRelativeTo(this);
        aboutDialog.setLayout(new BorderLayout());

        // Treść informacji o programie i autorze
        aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setText("Autor: " + AUTHOR + "\nPK - Zadanie 1");
        aboutDialog.add(aboutTextArea, BorderLayout.CENTER);

        // Przycisk do zamknięcia okna
        closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> aboutDialog.dispose());
        aboutDialog.add(closeButton, BorderLayout.SOUTH);

        // Wyświetlenie okna dialogowego
        aboutDialog.setVisible(true);
    }
}
