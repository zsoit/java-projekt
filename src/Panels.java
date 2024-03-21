import javax.swing.*;
import java.awt.*;


public class Panels extends JFrame  {

    private JPanel mainPanel, centerPanel;
    private JPanel topTextPanel, buttonPanel, footerPanel;
    private JLabel insertLabel, rowLabel, colLabel;
    private JSpinner rowSpinner, colSpinner;
    private JTextField insertText;
    private JButton addBtn, zeroBtn, insertBtn, saveBtn;
    private JTable myTable;
    private JScrollPane scrollTable;

    public JPanel createMainPanel(){
        JToolBar toolbarPanel = new Toolbar().getToolbar();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        mainPanel.add(this.createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(this.createFooter(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(800, 500));

        // centerPanel.setBounds(10,10,220,20);

        centerPanel.add(this.createTopTextPanel());
        centerPanel.add(this.createTable5x5());
        centerPanel.add(this.createButtonPanel());

        return centerPanel;
    }
    private JPanel createFooter(){
        footerPanel = new JPanel(new GridLayout(1, 2));
        footerPanel.add(new JLabel("Info Start Aplikacji"));
        footerPanel.add(new JLabel("Status ON"));
        return footerPanel;
    }

    private JPanel createTopTextPanel(){

        insertLabel = new JLabel("Wprowadź tekst: ");
        rowLabel = new JLabel("Numer wiersza: ");
        colLabel = new JLabel("Numer kolumny: ");
        insertText = new JTextField();
        colSpinner = new JSpinner();
        rowSpinner = new JSpinner();

        topTextPanel = new JPanel(new GridLayout(1, 6));

        topTextPanel.add(insertLabel);
        topTextPanel.add(insertText);

        topTextPanel.add(new JSeparator());


        topTextPanel.add(rowLabel);
        topTextPanel.add(rowSpinner);

        topTextPanel.add(new JSeparator());

        topTextPanel.add(colLabel);
        topTextPanel.add(colSpinner);

        return  topTextPanel;

    }

    private JPanel createButtonPanel(){
        buttonPanel = new JPanel(new GridLayout(6,2));

        addBtn = new JButton("Dodaj");
        zeroBtn = new JButton("Wyzeruj");
        insertBtn = new JButton("Wypełnij");
        saveBtn = new JButton("Zapisz");

        buttonPanel.add(addBtn);
        buttonPanel.add(zeroBtn);
        buttonPanel.add(insertBtn);
        buttonPanel.add(saveBtn);

        return buttonPanel;
    }

    private JScrollPane createTable5x5(){
        JFrame frame = new JFrame("Tabela 5x5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dane dla tabeli
        Object[][] data = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        // Nagłówki dla kolumn
        String[] columnHeaders = {"1", "2", "3", "4", "5"};

        // Tworzymy tabelę na podstawie danych i nagłówków
        myTable = new JTable(data, columnHeaders);

        // Ustawiamy preferowany rozmiar tabeli (opcjonalne)
        myTable.setPreferredScrollableViewportSize(new Dimension(800, 800));
        myTable.getTableHeader().setReorderingAllowed(false);

        // Ustawiamy możliwosc scrollowania
        scrollTable  = new JScrollPane(myTable);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10,50,600,10);

        return scrollTable;
    }
}
