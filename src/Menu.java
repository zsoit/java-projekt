
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame  {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("Plik");
    private JMenu editMenu = new JMenu("Edycja");
    private JMenu viewMenu = new JMenu("Widok");
    private JMenu helpMenu = new JMenu("Pomoc");

    private JMenuItem exitMenuItem = new JMenuItem("Wyjście");
    private JMenuItem saveMenuItem = new JMenuItem("Zapisz");
    private JMenuItem aboutApp = new JMenuItem("O programie");

    public JMenuBar createMenu(){
        // Menu

        fileMenu.add(exitMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(helpMenu);

        viewMenu.add(aboutApp);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;

    }

    public void Events(){


        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        aboutApp.addActionListener(new ActionListener() {
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
