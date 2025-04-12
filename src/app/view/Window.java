package app;

import app.model.IconException;

import app.view.*;
import app.view.Menu;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static javax.swing.SwingConstants.*;

public class Window extends JFrame implements ActionListener {
    protected   JButton jbtExit, jbtAbout, jbtHelpContext, jbtSave, jbtPrint, jbtSigma, jbtMean, jbtMin, jbtMax, jbtPieChart;
    protected   JButton addValue, addZeros, addFill, addSave, calcBtn;
    private StatusPanel statusPanel;
    private  JLabel labelValue, labelRow, labelCol;
    protected   JTextField jtfValue;
    protected JSlider jsRow;
    protected JSlider jsCol;
    private SpinnerNumberModel modelRow, modelCol;
    protected JTable table;

    private JPanel calcPanel;

    public JTextArea resultArea;
    private JScrollPane resultScroll;
    private Font font;

    protected Icons myIcons = new Icons();

    private ToolBarView toolBarView;

    protected app.view.Menu myMenu = new Menu(this, myIcons);

    public void WindowMainSetup(){
        setVisible(true);
        setTitle(Config.AUTHOR + " " + Config.STUDENT_ID + " - " + Config.APP_NAME);
        setSize(Config.WIDTH_WINDOW, Config.HEIGHT_WINDOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        resultArea.append(" ");
    }

    public Window() {

        //WindowMainSetup();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        JPanel contentPane = (JPanel) getContentPane(); //rzutowanie na JPanel
        contentPane.setLayout(new BorderLayout());


        try {

            myIcons.createIcons();
            createMenus();
            initGUI();

            contentPane.add(createToolBar() , BorderLayout.NORTH);
            contentPane.add(statusPanel, BorderLayout.SOUTH);
            contentPane.add(createCenterPanel(), BorderLayout.CENTER);


        } catch (IconException ie) {
            MessageDialog.Info("Błąd: ", "Błąd podczas wczytywania icon");

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.Error("Błąd: ", "Błąd podczas tworzenia GUI");
        }
    }


    private myTableView mytable = new myTableView();
    CalendarView myCalendar = new CalendarView();
    private void initGUI() {
        statusPanel = new StatusPanel();

        setTextField();
        setSlider();
        table = mytable.getTable();
        setButtons();
        setCalcPanel();
        setResultArea();



    }

    private void setSlider(){
        labelRow = new JLabel("Nr wiersza", LEFT);
        labelCol = new JLabel("Nr kolumny", LEFT);

        // Suwak dla wierszy
        jsRow = new JSlider(HORIZONTAL, 1, 5, 1);
        jsRow.setMajorTickSpacing(1);  // Duże odstępy co 1
        jsRow.setMinorTickSpacing(1);  // Małe odstępy co 1
        jsRow.setPaintTicks(true);
        jsRow.setPaintLabels(true);

        // Suwak dla kolumn
        jsCol = new JSlider(HORIZONTAL, 1, 5, 1);
        jsCol.setMajorTickSpacing(1);  // Duże odstępy co 1
        jsCol.setMinorTickSpacing(1);  // Małe odstępy co 1
        jsCol.setPaintTicks(true);
        jsCol.setPaintLabels(true);
    }

    private void setTextField(){
        labelValue = new JLabel("Wprowadź liczbę", LEFT);
        jtfValue = new JTextField("0");
        jtfValue.setHorizontalAlignment(RIGHT);
    }

    private void setButtons(){

        addValue = new CustomButton("Dodaj", myIcons.iconAdding, this);
        addZeros = new CustomButton("Wyzeruj", myIcons.iconZero, this);
        addFill = new CustomButton("Wypełnij", myIcons.iconFill, this);
        addSave = new CustomButton("Zapisz", myIcons.iconSave_Doc, this);
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

                // wyswitlenie daty w JTextArea
                resultAreaPrint("Data - " + dateString + "\n ");


            }
        });

        return kalendarz;
    }


    private void setCalcPanel(){


//        JDateChooser kalendarz = myCalendar.getCalendar();

        JDateChooser kalendarz = setKalendarz();

//        System.out.print(myCalendar.getTextDate());
//
//
//       setKalendarz();



        JLabel labelKalendarz = new JLabel("Wybierz date: ");

        JLabel labelKolo = new JLabel("Rozkład wartości w tabeli: ");
        calcPanel = new JPanel(new GridLayout(1, 4));
        JLabel calculation = new JLabel("Obliczenia:");
        String[] operation = {"Wybierz operację", "Dodawanie", "Min", "Max", "Średnia"};

        JComboBox<String> comboBox = new JComboBox<>(operation);
        comboBox.setSelectedIndex(0); // Ustawienie domyślnego tekstu
        comboBox.setBounds(50, 30, 80, 30);

        JLabel emptyLabel = new JLabel();
        JLabel emptyLabel1 = new JLabel();


        calcBtn = new JButton("Oblicz");
        calcBtn.addActionListener(this);


        calcPanel.add(calculation);
        calcPanel.add(comboBox);
        calcPanel.add(calcBtn);
        calcPanel.add(emptyLabel);
        calcPanel.add(labelKalendarz);
        calcPanel.add(kalendarz);
        calcPanel.add(emptyLabel1);

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





        // Zwrócenie skonfigurowanego panelu
        return jp;
    }


    private void createMenus() {
        //utworzenie paska manu
        JMenuBar  menuBar = myMenu.getMenu();
        setJMenuBar(menuBar);
    }


    private void createCustomBtn(){
        // Tworzenie przycisków za pomocą klasy CustomToolBarButton
        jbtSave = new CustomToolBarButton("Zapis pliku", myIcons.iconSave, this);
        jbtPrint = new CustomToolBarButton("Drukowanie", myIcons.iconPrint, this);
        jbtExit = new CustomToolBarButton("Zamknięcie aplikacji", myIcons.iconExit, this);
        jbtSigma = new CustomToolBarButton("Dodawanie", myIcons.iconSigma, this);
        jbtMean = new CustomToolBarButton("Średnia", myIcons.iconMean, this);
        jbtMin = new CustomToolBarButton("Minimum", myIcons.iconMin, this);
        jbtMax = new CustomToolBarButton("Maximum", myIcons.iconMax, this);
        jbtHelpContext = new CustomToolBarButton("Kontekst pomocy", myIcons.iconHelp, this);
        jbtAbout = new CustomToolBarButton("Informacje o autorze", myIcons.iconAbout, this);
        jbtPieChart = new CustomToolBarButton("Wykres kołowy", myIcons.iconPieChart, this);
    }
    private JToolBar createToolBar() {
        JToolBar jbt = new JToolBar(HORIZONTAL);
        jbt.setFloatable(false);

        createCustomBtn();

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
        jbt.add(jbtPieChart);

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



    protected void resultAreaPrint(String desc){
        resultArea.append(desc);
    }


    protected void zoomIn() {
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    protected void zoomOut() {
        setSize(680, 480);
        setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
