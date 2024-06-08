package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class Window extends JFrame implements ActionListener  {


    private  JButton jbtExit, jbtAbout, jbtHelpContext, jbtSave, jbtPrint, jbtSigma, jbtMean, jbtMin, jbtMax;
    private  JButton addValue, addZeros, addFill, addSave, calcBtn;
    private StatusPanel statusPanel;
    
    private  JLabel labelValue, labelRow, labelCol;
    private  JTextField jtfValue;
    private JSlider jsRow;
    private JSlider jsCol;
    private SpinnerNumberModel modelRow, modelCol;
    private JTable table;

    private JPanel calcPanel;

    protected JTextArea resultArea;
    private JScrollPane resultScroll;
    private Font font;

    private Icons myIcons = new Icons();
    private Calculation calculation = new Calculation();
    private Menu myMenu = new Menu(this, myIcons);
    private AboutWindow aboutWindow = new AboutWindow();



    public Window() {


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        JPanel cp = (JPanel) getContentPane(); //rzutowanie na JPanel
        cp.setLayout(new BorderLayout());


        try {
            myIcons.createIcons();
            createMenus();
            initGUI();

            // Dodaje pasek narzędzi do kontenera głównego (content pane) na północy (górze) okna
            cp.add(createToolBar(), BorderLayout.NORTH);

            // Dodaje panel statusu do kontenera głównego (content pane) na południu (dole) okna
            cp.add(statusPanel, BorderLayout.SOUTH);

           // Dodaje panel centralny do kontenera głównego (content pane) w centrum okna
            cp.add(createCenterPanel(), BorderLayout.CENTER);

        } catch (IconException ie) {
            ShowMessageDialog("Błąd: ", "Błąd podczas wczytywania icon");
        } catch (Exception e) {
            e.printStackTrace();
            ShowMessageDialog("Błąd: ", "Błąd podczas tworzenia GUI");
        }
    }

    private void initGUI() {
        statusPanel = new StatusPanel();

        setTextField();
        setSlider();
        setTable();
        calculation.resetTable(table);
        setButtons();
        setCalcPanel();
        setResultArea();
    }

    private void setTable(){


        table = new JTable(5, 5);
        table.setEnabled(false);
        table.setRowHeight(table.getRowHeight() + 11);
        table.setBackground(new Color(211, 211, 211));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);


        // Wyśrodkowanie tytułów kolumn znajdujących się na górze tabeli
        // Pobiera domyślny renderer dla nagłówków kolumn, rzutuje go na typ DefaultTableCellRenderer i ustawia wyrównanie poziome na środek.
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Ustawienie renderera komórek dla danych
        // Tworzy nowy renderer dla komórek tabeli
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        // Ustawia wyrównanie poziome zawartości komórek na prawą stronę
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        // Ustawia rightRenderer jako domyślny renderer dla wszystkich komórek zawierających obiekty typu Object
        table.setDefaultRenderer(Object.class, rightRenderer);

        new JScrollPane(table);




    }

    private void setSlider(){
        labelRow = new JLabel("Nr wiersza", JLabel.LEFT);
        labelCol = new JLabel("Nr kolumny", JLabel.LEFT);

        // Suwak dla wierszy
        jsRow = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        jsRow.setMajorTickSpacing(1);  // Duże odstępy co 1
        jsRow.setMinorTickSpacing(1);  // Małe odstępy co 1
        jsRow.setPaintTicks(true);
        jsRow.setPaintLabels(true);

        // Suwak dla kolumn
        jsCol = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        jsCol.setMajorTickSpacing(1);  // Duże odstępy co 1
        jsCol.setMinorTickSpacing(1);  // Małe odstępy co 1
        jsCol.setPaintTicks(true);
        jsCol.setPaintLabels(true);
    }

    private void setTextField(){
        labelValue = new JLabel("Wprowadź liczbę", JLabel.LEFT);
        jtfValue = new JTextField("0");
        jtfValue.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void setButtons(){

        //definiowanie czcionki przycisków
        font = new Font("Helvetica Neue", Font.BOLD, 12);

        addValue = new JButton("Dodaj", myIcons.iconAdding);
        addValue.addActionListener(this);
        addValue.setFont(font);

        addZeros = new JButton("Wyzeruj", myIcons.iconZero);
        addZeros.addActionListener(this);
        addZeros.setFont(font);


        addFill = new JButton("Wypełnij", myIcons.iconFill);
        addFill.addActionListener(this);
        addFill.setFont(font);

        addSave = new JButton("Zapisz", myIcons.iconSave_Doc);
        addSave.addActionListener(this);
        addSave.setFont(font);
    }

    private JDateChooser setKalendarz(){
        JDateChooser kalendarz = new JDateChooser();
        kalendarz.setDateFormatString("yyyy-MM-dd");
        Date test = java.sql.Date.valueOf(LocalDate.now());

        kalendarz.setDate(test);
        kalendarz.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt){
                Date dataCurrent = kalendarz.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dataCurrent);

                // wyswitlenie daty
                String result =  "Data - " + dateString + "\n ";
                resultArea.append(result);

            }
        });

        return kalendarz;
    }

    private void setCalcPanel(){
        JDateChooser kalendarz = setKalendarz();
        JLabel labelKalendarz = new JLabel("Wybierz date: ");
        calcPanel = new JPanel(new GridLayout(1, 4));
        JLabel calculation = new JLabel("Obliczenia:");
        String[] operation = {"Wybierz operację", "Dodawanie", "Min", "Max", "Średnia"};
        JComboBox<String> comboBox = new JComboBox<>(operation);
        comboBox.setSelectedIndex(0); // Ustawienie domyślnego tekstu
        comboBox.setBounds(50, 30, 100, 30);
        JLabel emptyLabel = new JLabel();

        calcBtn = new JButton("Oblicz");
        calcBtn.addActionListener(this);


        calcPanel.add(calculation);
        calcPanel.add(comboBox);
        calcPanel.add(calcBtn);
        calcPanel.add(emptyLabel);
        calcPanel.add(labelKalendarz);
        calcPanel.add(kalendarz);

    }



    private void setResultArea(){
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        resultScroll = new JScrollPane(resultArea);
        resultScroll.setViewportView(resultArea); // Ustawienie widoku na JTextArea

        resultArea.setCaretPosition(resultArea.getDocument().getLength()); //automatyczne przewijanie

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Uzyskany rezultat");
        // Ustawienie wyśrodkowania tytułu
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        // Ustawienie ramki z tytułem na komponencie
        resultScroll.setBorder(titledBorder);
    }

    private JPanel createCenterPanel() {



        // Tworzenie nowego panelu
        JPanel jp = new JPanel();

        // Definiowanie układu formy z kolumnami i wierszami o określonych odstępach i szerokościach
        FormLayout formLayout = new FormLayout(
                // Definicja kolumn (1 - 14)
                // 1     2     3      4         5       6     7      8      9     10    11     12     13    14
                "5dlu, pref, 2dlu, 50dlu, 10dlu:grow, pref, 3dlu, 40dlu, 20dlu, 10dlu, pref, 3dlu, 40dlu, 5dlu",
                // Definicja wierszy (1 - 17)
                // 1     2     3      4     5      6    7     8     9     10    11    12    13    14       15       16     17
                "5dlu, pref, 10dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 5dlu, 5dlu, pref, 10dlu, pref:grow, 8dlu, 5dlu");

        // Ustawienie układu na panelu
        jp.setLayout(formLayout);

        // Tworzenie obiektu do zarządzania pozycjonowaniem komponentów w siatce
        CellConstraints cc = new CellConstraints();

        // Dodawanie komponentów do panelu z określonymi pozycjami w układzie

        // Etykieta i pole tekstowe dla wartości
        jp.add(labelValue, cc.xy(2, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jtfValue, cc.xy(4, 2, CellConstraints.FILL, CellConstraints.FILL));


        // Etykieta i suwak dla wierszy
        jp.add(labelRow, cc.xy(6, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jsRow, cc.xy(8, 2, CellConstraints.FILL, CellConstraints.FILL));

        // Etykieta i suwak dla kolumn
        jp.add(labelCol, cc.xy(11, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jsCol, cc.xy(13, 2, CellConstraints.FILL, CellConstraints.FILL));

        // Dodanie tabeli w JScrollPane zajmującej większy obszar
        jp.add(new JScrollPane(table), cc.xywh(2, 4, 8, 7, CellConstraints.FILL, CellConstraints.FILL));

        // Dodanie przycisków do dodawania wartości, zer, wypełnienia i zapisu
        jp.add(addValue, cc.xyw(11, 4, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addZeros, cc.xyw(11, 6, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addFill, cc.xyw(11, 8, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addSave, cc.xyw(11, 10, 3, CellConstraints.FILL, CellConstraints.FILL));

        // Dodanie panelu kalkulacyjnego
        jp.add(calcPanel, cc.xyw(2, 13, 10, CellConstraints.FILL, CellConstraints.FILL));


        // Dodanie JScrollPane dla wyników, zajmującego większy obszar
        jp.add(resultScroll, cc.xyw(2, 15, 12, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(resultScroll, cc.xyw(2, 15, 12, CellConstraints.FILL, CellConstraints.FILL));


        // Zwrócenie skonfigurowanego panelu
        return jp;
    }


    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jb = new JButton("", icon);
        jb.setToolTipText(tooltip);
        jb.addActionListener(this);
        return jb;
    }

    private void createMenus() {
        //utworzenie paska manu
        JMenuBar  menuBar = myMenu.getMenu();
        setJMenuBar(menuBar);
    }

    private JToolBar createToolBar() {
        JToolBar jbt = new JToolBar(JToolBar.HORIZONTAL);
        jbt.setFloatable(false);

        jbtSave = createJButtonToolBar("Zapis pliku", myIcons.iconSave);
        jbtPrint = createJButtonToolBar("Drukowanie", myIcons.iconPrint);
        jbtExit = createJButtonToolBar("Zamknięcie aplikacji", myIcons.iconExit);
        jbtSigma = createJButtonToolBar("Dodawanie", myIcons.iconSigma);
        jbtMean = createJButtonToolBar("Średnia", myIcons.iconMean);
        jbtMin = createJButtonToolBar("Minimum", myIcons.iconMin);
        jbtMax = createJButtonToolBar("Maximum", myIcons.iconMax);
        jbtHelpContext = createJButtonToolBar("Kontekst pomocy", myIcons.iconHelp);
        jbtAbout = createJButtonToolBar("Informacje o autorze", myIcons.iconAbout);

        jbt.add(Box.createHorizontalStrut(5));
        jbt.add(jbtSave);
        jbt.add(jbtPrint);
        jbt.add(jbtExit);
        jbt.addSeparator();
        jbt.add(jbtSigma);
        jbt.add(jbtMean);
        jbt.add(jbtMin);
        jbt.add(jbtMax);
        jbt.addSeparator();
        jbt.add(jbtHelpContext);
        jbt.add(jbtAbout);

        return jbt;
    }

    public void closeWindow() {
        // utworzenie okna dialogowego z zapytanie o zamkniecie projektu
        int value = JOptionPane.showOptionDialog(
                this,
                "Czy chcesz zamknąć aplikację", //komunikat
                "Uwaga", // nagłówek okna
                JOptionPane.YES_NO_CANCEL_OPTION, //typ opcji
                JOptionPane.WARNING_MESSAGE, //typ komunikatu
                null, //domyślna ikona
                new String[]{"Tak", "Nie"},
                "Tak"); // inicjacja aktywnego przycisku

        if (value == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }




    @Override
    public void actionPerformed(ActionEvent event) {


        String result;
        result = " ";

        if (event.getSource() == myMenu.saveMenuItem || event.getSource() == jbtSave || event.getSource() == addSave) {
            saveTableToFile(table);

        }
        if (event.getSource() == myMenu.printMenuItem || event.getSource() == jbtPrint) {
            ShowMessageDialog("Drukowanie", "Plik został wydrukowany pomyślnie!");
        }
        if (event.getSource() == myMenu.exitMenuItem || event.getSource() == jbtExit) {
            closeWindow();
        }
        if (event.getSource() == myMenu.helpMenuItem || event.getSource() == jbtHelpContext) {
//            ShowMessageDialog("Kontekst pomocy", Config.DOCS_INFO);
            AboutWindow aboutWindow = new AboutWindow(); // Tworzenie okna "Informacje o programie"
            aboutWindow.setVisible(true);

        }
        if (event.getSource() == myMenu.aboutMenuItem || event.getSource() == jbtAbout) {

            String ABOUT_AUTHOR = "Wersja: " + Config.VERSION + " \nAutor: " + Config.AUTHOR + "\nKierunek: " + Config.STUDIES_MAJOR + "\nKontakt: " + Config.EMAIL;
            ShowMessageDialog("Informacje o autorze", ABOUT_AUTHOR);

        }
        if (event.getSource() == myMenu.zoominMenuItem) {
            zoomOut();
        }
        if (event.getSource() == myMenu.zoomoutMenuItem) {
            zoomIn();
        }


        if (event.getSource() == addZeros) {
            result = calculation.resetTable(table);
        }
        if (event.getSource() == addValue) {


            try {
                int rowIndex = (int) jsRow.getValue();
                int colIndex = (int) jsCol.getValue();
                int value = Integer.parseInt(jtfValue.getText());
                result = calculation.setValueTable(rowIndex, colIndex, value, table);

            } catch (NumberFormatException ex) {
               ShowMessageDialog("Błąd", "Proszę wprowadzić poprawną liczbę całkowitą");
            }



        }

        if (event.getSource() == addFill){
            try {
                int rowIndex = (int) jsRow.getValue();
                int colIndex = (int) jsCol.getValue();
                int value = Integer.parseInt(jtfValue.getText());
                result = calculation.fillTable(table, value);

            } catch (NumberFormatException ex) {
                ShowMessageDialog("Błąd", "Proszę wprowadzić poprawną liczbę całkowitą");
            }
        }

        if (event.getSource() == jbtSigma || event.getSource() == myMenu.addMenuItem || event.getSource() == addValue) {
            result =  calculation.additionalElements(table);
        }
        if (event.getSource() == jbtMean || event.getSource() == myMenu.meanMenuItem) {
            result =  calculation.avgElements(table);
        }
        if (event.getSource() == jbtMin || event.getSource() == myMenu.minMenuItem) {
            result = calculation.minElements(table);
        }
        if (event.getSource() == jbtMax || event.getSource() == myMenu.maxMenuItem) {
            result =  calculation.maxElements(table);
        }


        if (event.getSource() == calcBtn) {
            JComboBox<String> comboBox = (JComboBox<String>) ((JPanel) calcBtn.getParent()).getComponent(1);
            String selectedOperation = (String) comboBox.getSelectedItem();
            switch (selectedOperation) {
                case "Dodawanie":
                    result =  calculation.additionalElements(table);
                    break;
                case "Średnia":
                    result =  calculation.avgElements(table);

                    break;
                case "Min":
                    result =  calculation.minElements(table);

                    break;
                case "Max":
                    result =  calculation.maxElements(table);

                    break;
                default:
                    ShowMessageDialog("Błąd", "Wybierz operację z listy");
                    break;

            }


        }


        resultAreaAlert(result);

    }

    protected void resultAreaAlert(String desc){
        resultArea.append(desc);
    }

    protected void ShowMessageDialog(String title1, String title2){
        JOptionPane.showMessageDialog(this,  title2,  title1, JOptionPane.INFORMATION_MESSAGE);
    }


  protected void zoomIn() {
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

   protected void zoomOut() {
        setSize(680, 480);
        setLocationRelativeTo(null);
    }


    private static String defaultFilePath = null; // domyślna ścieżka do pliku

    public void saveTableToFile(JTable table) {
        // Jeśli nie ma domyślnej ścieżki, poproś użytkownika o wybór lokalizacji pliku
        if (defaultFilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Wybierz lokalizację pliku");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                defaultFilePath = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
            } else {
                ShowMessageDialog("Zapis", "Anulowano zapis do pliku");
                return;
            }
        }

        try {
            FileWriter fw = new FileWriter(defaultFilePath);
            BufferedWriter bw = new BufferedWriter(fw);

            TableModel model = table.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            // Zapisz nagłówki kolumn
            for (int i = 0; i < colCount; i++) {
                bw.write(model.getColumnName(i));
                if (i < colCount - 1)
                    bw.write("\t"); // dodajemy tabulator między nagłówkami kolumn
            }
            bw.newLine(); // nowa linia po nagłówkach

            // Zapisz dane
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = model.getValueAt(row, col);
                    // Sprawdź czy wartość jest null, jeśli tak, zapisz pusty ciąg znaków
                    if (value != null)
                        bw.write(value.toString());
                    if (col < colCount - 1)
                        bw.write("\t"); // dodajemy tabulator między komórkami
                }
                bw.newLine(); // nowa linia po zapisaniu wszystkich danych w wierszu
            }

            bw.close();
            fw.close();
            ShowMessageDialog("Zapis", "Plik został zapisany pomyślnie");
        } catch (IOException e) {
            ShowMessageDialog("Błąd", "Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());

        }
    }


}