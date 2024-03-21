import javax.swing.*;
import java.awt.*;


public class Panels extends JFrame  {

    private JPanel mainPanel, centerPanel;
    private JPanel topTextPanel, buttonPanel, footerPanel;

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
        JLabel insertNumberLabel = new JLabel("Wprowadz Tekst");
//        insertNumberLabel.setBounds(10,10,220,20);
        insertNumberLabel.add(new JTextField());

        JTextField insertNumber = new JTextField();
//        insertNumber.setBounds(5,5,10,10);

        topTextPanel = new JPanel(new GridLayout(1, 6));

        topTextPanel.add(insertNumberLabel);
        topTextPanel.add(insertNumber);

        topTextPanel.add(new JSeparator());


        topTextPanel.add( new JLabel("Numer wiersza"));
        topTextPanel.add(new JSpinner());

        topTextPanel.add(new JSeparator());

        topTextPanel.add( new JLabel("Numer kolumny"));
        topTextPanel.add(new JSpinner());

        return  topTextPanel;

    }

    private JPanel createButtonPanel(){
        buttonPanel = new JPanel(new GridLayout(6,2));

        JButton addBtn = new JButton("Dodaj");
        JButton zeroBtn = new JButton("Wyzeruj");
        JButton insertBtn = new JButton("Wypełnij");
        JButton saveBtn = new JButton("Zapisz");

        buttonPanel.add(addBtn);
        buttonPanel.add(zeroBtn);
        buttonPanel.add(insertBtn);
        buttonPanel.add(saveBtn);
        return buttonPanel;
    }

    private JScrollPane createTable5x5(){
        JFrame frame = new JFrame("Tabela 5x5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dane dla tabeli (możesz je zastąpić własnymi danymi)
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
        JTable table = new JTable(data, columnHeaders);

        // Ustawiamy preferowany rozmiar tabeli (opcjonalne)
        table.setPreferredScrollableViewportSize(new Dimension(800, 800));

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane przewijanie_tabeli = new JScrollPane(table);
        przewijanie_tabeli.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        przewijanie_tabeli.setBounds(10,50,600,10);

        return przewijanie_tabeli;
    }
}
