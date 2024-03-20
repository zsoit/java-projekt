import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final String APP_NAME = "PA - Zadanie 1 GUI";
    public MainFrame() {

        // menu, pobierz menu
        Menu menuItem = new Menu();
        JMenuBar getMenu = menuItem.createMenu();

        //  Ustawienie tytułu okna
        setTitle(APP_NAME);

        // Ustawienia rozmiaru okna
        setSize(900, 900);

        setResizable(true);
        setLocationRelativeTo(null);

        // Ustawienie widoczności okna
        setVisible(true);

        this.centerWindow();
        this.exitWindow();

        setJMenuBar(getMenu);


    }

    public static void main(String[] args) {
        // Utworzenie obiektu ramki głównej
        MainFrame frame = new MainFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // FOOTER PANEL
        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        footerPanel.add(new JLabel("Info Start Aplikacji"));
        footerPanel.add(new JLabel("Status ON"));


        JLabel insertNumberLabel = new JLabel("Wprowadz Tekst");
        insertNumberLabel.setBounds(10,10,220,20);
        insertNumberLabel.add(new JTextField());

        JTextField insertNumber = new JTextField();
        insertNumber.setBounds(10,10,220,20);

        JPanel topTextPanel = new JPanel(new GridLayout(2, 3));

        topTextPanel.add(insertNumberLabel);
        topTextPanel.add( new JLabel("Numer wiersza"));
        topTextPanel.add( new JLabel("Numer kolumny"));

        JTable myTable = frame.Table5x5();

        topTextPanel.add(myTable);

        //  PRXYCISKI

        JPanel buttonPanel = new JPanel(new GridLayout(4,2));

        JButton addBtn = new JButton("Dodaj");
        JButton zeroBtn = new JButton("Wyzeruj");
        JButton insertBtn = new JButton("Wypełnij");
        JButton saveBtn = new JButton("Zapisz");

        buttonPanel.add(addBtn);
        buttonPanel.add(zeroBtn);
        buttonPanel.add(insertBtn);
        buttonPanel.add(saveBtn);

        topTextPanel.add(buttonPanel);

        
        JPanel centerPanel = new JPanel(new GridLayout(7, 2));
        centerPanel.setPreferredSize(new Dimension(800, 500));

        centerPanel.add(topTextPanel);


        JToolBar toolbarPanel = new Toolbar().getToolbar();

        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.pack();


    }

    public void centerWindow(){
        // Pobranie rozmiaru ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Obliczenie położenia okna na środku ekranu
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

        // Ustawienie położenia okna
        setLocation(x, y);
    }
    public void exitWindow(){
        // Dodanie obsługi zdarzenia zamknięcia okna
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0); // Zamknięcie aplikacji przy zamknięciu okna
            }
        });

    }
    public JTable Table5x5(){
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
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));



        return table;
    }
}
