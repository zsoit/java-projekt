import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {
    private JMenuBar myMenuBar;
    private JToolBar myToolbar;
    private JPanel myPanel;
    private JTable myTable;
    private JTextArea wpisanie_liczby, wynik_operacji;
    private JSlider wybierz_wiersz, wybierz_kolumne;
    private DefaultListModel<String> l1;
    private JDialog myDialog;

    public MainAppFrame() {
        initializeFrame();
        createMenuBar();
        createToolbar();
        createPanel();
        createTable();
        createDialog();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("PA - projekt");
        setSize(755, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void createMenuBar() {
        myMenuBar = new JMenuBar();
        // Dodawanie pozycji menu do paska menu
        setJMenuBar(myMenuBar);
    }

    private void createToolbar() {
        myToolbar = new JToolBar();
        // Dodawanie przycisków do paska narzędzi
        add(myToolbar, BorderLayout.NORTH);
    }

    private void createPanel() {
        myPanel = new JPanel();
        // Dodawanie komponentów do panelu
        add(myPanel);
    }

    private void createTable() {
        // Inicjalizacja i dodawanie tabeli do panelu
    }

    private void createDialog() {
        myDialog = new JDialog(this, "Projekt 1 - GUI", true);
        myDialog.setResizable(false);
        myDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Dodawanie komponentów do okna dialogowego
    }
}
