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
    private JLabel infoLabel, statusLabel;

    private static final Color BLUE = new Color(200, 200, 255);
    private static final Color GREEN = new Color(200, 255, 200);


    public JPanel createMainPanel(){



//        mainPanel = new JPanel(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Ustawianie układu w pionie


//        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
//        mainPanel.add(this.createCenterPanel(), BorderLayout.CENTER);

        mainPanel.add(this.createHeaderPanel());
        mainPanel.add(this.createFirstPanel());
        mainPanel.add(this.createSecondPanel());
        mainPanel.add(this.createFooter());



        return mainPanel;
    }

    private JPanel createCenterPanel(){
        centerPanel = new JPanel();
//        centerPanel.setPreferredSize(new Dimension(100, 500));

        // centerPanel.setBounds(10,10,220,20);

//        centerPanel.add(this.createTopTextPanel());
        centerPanel.add(this.createTable5x5());
        centerPanel.add(this.createButtonPanel());

        return centerPanel;
    }


    private JPanel createHeaderPanel(){
        JToolBar toolbarPanel = new Toolbar().getToolbar();
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.add(toolbarPanel);
        return centerPanel;
    }
    private JPanel createFirstPanel(){
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.add(this.createTopTextPanel());
        return centerPanel;
    }

    private JPanel createSecondPanel(){
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.add(this.createTable5x5());
        centerPanel.add(this.createButtonPanel());

        return centerPanel;
    }
    private JPanel createFooter(){
        infoLabel = new JLabel("Info Start Aplikacji");
        statusLabel = new JLabel("Status ON");

        footerPanel = new JPanel(new GridLayout(1, 2));

        footerPanel.add(infoLabel);
        footerPanel.add(statusLabel);

        return footerPanel;
    }

    private JPanel createTopTextPanel(){

        insertLabel = new JLabel("Wprowadź tekst: ");
        rowLabel = new JLabel("Numer wiersza: ");
        colLabel = new JLabel("Numer kolumny: ");
        insertText = new JTextField();
        colSpinner = new JSpinner();
        rowSpinner = new JSpinner();

        insertText.setPreferredSize(new Dimension(50, insertText.getPreferredSize().height));
        rowSpinner.setPreferredSize(new Dimension(50, rowSpinner.getPreferredSize().height));
        colSpinner.setPreferredSize(new Dimension(50, colSpinner.getPreferredSize().height));


        topTextPanel = new JPanel();

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
        buttonPanel = new JPanel(new GridLayout(4,1, 20, 20));

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
        myTable.setPreferredScrollableViewportSize(new Dimension(600, 100));
        myTable.getTableHeader().setReorderingAllowed(false);

        // Ustawiamy możliwosc scrollowania
        scrollTable  = new JScrollPane(myTable);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTable.setBounds(10,50,600,10);

        return scrollTable;
    }
}


