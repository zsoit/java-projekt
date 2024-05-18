package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame implements ActionListener  {

    private  JButton jbtExit, jbtAbout, jbtHelpContext, jbtSave, jbtPrint, jbtSigma, jbtMean, jbtMin, jbtMax;
    private  JButton addValue, addZeros, addFill, addSave, obliczBtn;
    private StatusPanel statusPanel;

    private  JLabel labelValue, labelRow, labelCol;
    private  JTextField jtfValue;
    private  JSpinner jsRow, jsCol;
    private SpinnerNumberModel modelRow, modelCol;
    protected JTable table;

    private JPanel panelOblicz;

    protected JTextArea obszarWynikow;
    private JScrollPane przewijanieWynikow;
    private Font font;

    private Icons myIcons = new Icons();
    private WindowModel model = new WindowModel();
    private Menu myMenu = new Menu(this, myIcons);



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
            cp.add(createToolBar(), BorderLayout.NORTH);
            cp.add(statusPanel, BorderLayout.SOUTH);
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

        labelValue = new JLabel("Wprowadź liczbę", JLabel.LEFT);
        labelRow = new JLabel("Numer wiersza", JLabel.LEFT);
        labelCol = new JLabel("Numer kolumny", JLabel.LEFT);

        jtfValue = new JTextField("0");
        jtfValue.setHorizontalAlignment(JTextField.RIGHT);

        modelRow = new SpinnerNumberModel(1, 1, 5, 1); //(początkowa wartość, minimum, maksimum, krok)
        modelCol = new SpinnerNumberModel(1, 1, 5, 1);
        jsRow = new JSpinner(modelRow);
        jsCol = new JSpinner(modelCol);


        table = new JTable(5, 5);
        table.setEnabled(false);
        table.setRowHeight(table.getRowHeight() + 11);
        table.setBackground(new Color(211, 211, 211));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);


        // Wyśrodkowanie tytułów kolumn znajdujących się na górze tabeli
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Ustawienie renderera komórek dla danych
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.setDefaultRenderer(Object.class, rightRenderer);

        new JScrollPane(table);

        //definiowanie czcionki przycisków
        font = new Font("Helvetica Neue", Font.BOLD, 12);

        addValue = new JButton("Dodaj", myIcons.iconAdding);
        addValue.addActionListener(this);
        addValue.setFont(font);
        ;

        addZeros = new JButton("Wyzeruj", myIcons.iconZero);
        addZeros.addActionListener(this);
        addZeros.setFont(font);


        addFill = new JButton("Wypełnij", myIcons.iconFill);
        addFill.addActionListener(this);
        addFill.setFont(font);

        addSave = new JButton("Zapisz", myIcons.iconSave_Doc);
        addSave.addActionListener(this);
        addSave.setFont(font);

        panelOblicz = new JPanel(new GridLayout(1, 4));
        JLabel obliczenia = new JLabel("Obliczenia:");
        String[] operacje = {"Wybierz operację", "Dodawanie", "Min", "Max", "Średnia"};
        JComboBox<String> comboBox = new JComboBox<>(operacje);
        comboBox.setSelectedIndex(0); // Ustawienie domyślnego tekstu
        comboBox.setBounds(50, 30, 200, 30);
        JLabel pustyLabel = new JLabel();

        obliczBtn = new JButton("Oblicz");
        obliczBtn.addActionListener(this);


        panelOblicz.add(obliczenia);
        panelOblicz.add(comboBox);
        panelOblicz.add(obliczBtn);
        panelOblicz.add(pustyLabel);


        obszarWynikow = new JTextArea();
        obszarWynikow.setEditable(false);


        przewijanieWynikow = new JScrollPane(obszarWynikow);
        przewijanieWynikow.setViewportView(obszarWynikow); // Ustawienie widoku na JTextArea

        obszarWynikow.setCaretPosition(obszarWynikow.getDocument().getLength()); //automatyczne przewijanie

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Uzyskany rezultat");
        // Ustawienie wyśrodkowania tytułu
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        // Ustawienie ramki z tytułem na komponencie
        przewijanieWynikow.setBorder(titledBorder);
    }

    private JPanel createCenterPanel() {
        JPanel jp = new JPanel();
        FormLayout formLayout = new FormLayout(
                //1     2     3      4         5       6     7      8      9     10    11     12     13    14
                "5dlu, pref, 2dlu, 50dlu, 10dlu:grow, pref, 3dlu, 40dlu, 20dlu, 10dlu, pref, 3dlu, 40dlu, 5dlu",
                "5dlu, pref, 10dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 5dlu, 5dlu, pref, 10dlu, pref:grow, 8dlu , 5dlu");
        //1     2     3      4     5      6    7     8     9     10    11    12    13    14       15       16     17

        jp.setLayout(formLayout);


        CellConstraints cc = new CellConstraints();
        jp.add(labelValue, cc.xy(2, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jtfValue, cc.xy(4, 2, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(labelRow, cc.xy(6, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jsRow, cc.xy(8, 2, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(labelCol, cc.xy(11, 2, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(jsCol, cc.xy(13, 2, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(new JScrollPane(table), cc.xywh(2, 4, 8, 7, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addValue, cc.xyw(11, 4, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addZeros, cc.xyw(11, 6, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addFill, cc.xyw(11, 8, 3, CellConstraints.FILL, CellConstraints.FILL));
        jp.add(addSave, cc.xyw(11, 10, 3, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(panelOblicz, cc.xyw(2, 13, 10, CellConstraints.FILL, CellConstraints.FILL));

        jp.add(przewijanieWynikow, cc.xyw(2, 15, 12, CellConstraints.FILL, CellConstraints.FILL));


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
            ShowMessageDialog("Zapis", "Plik został zapisany pomyślnie!");
        }
        if (event.getSource() == myMenu.printMenuItem || event.getSource() == jbtPrint) {
            ShowMessageDialog("Drukowanie", "Plik został wydrukowany pomyślnie!");
        }
        if (event.getSource() == myMenu.exitMenuItem || event.getSource() == jbtExit) {
            closeWindow();
        }
        if (event.getSource() == myMenu.helpMenuItem || event.getSource() == jbtHelpContext) {
            ShowMessageDialog("Kontekst pomocy", Config.DOCS_INFO);
        }
        if (event.getSource() == myMenu.aboutMenuItem || event.getSource() == jbtAbout) {
            ShowMessageDialog("Informacje o autorze", Config.ABOUT_AUTHOR);

        }
        if (event.getSource() == myMenu.zoominMenuItem) {
            zoomOut();
        }
        if (event.getSource() == myMenu.zoomoutMenuItem) {
            zoomIn();
        }


        if (event.getSource() == addZeros) {
            result = model.resetTable(table);
        }
        if (event.getSource() == addFill) {
            int indeksWiersza = (int) jsRow.getValue();
            int indeksKolumny = (int) jsCol.getValue();
            int wartosc = Integer.parseInt(jtfValue.getText());

            result = model.setValueTable(indeksWiersza, indeksKolumny, wartosc, obszarWynikow, table);

        }
        if (event.getSource() == jbtSigma || event.getSource() == myMenu.addMenuItem || event.getSource() == addValue) {
            result =  model.additionalElements(table);
        }
        if (event.getSource() == jbtMean || event.getSource() == myMenu.meanMenuItem) {
            result =  model.avgElements(table);
        }
        if (event.getSource() == jbtMin || event.getSource() == myMenu.minMenuItem) {
            result = model.minElements(table);
        }
        if (event.getSource() == jbtMax || event.getSource() == myMenu.maxMenuItem) {
            result =  model.maxElements(table);
        }


        if (event.getSource() == obliczBtn) {
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBox = (JComboBox<String>) ((JPanel) obliczBtn.getParent()).getComponent(1);
            String selectedOperation = (String) comboBox.getSelectedItem();
            switch (selectedOperation) {
                case "Dodawanie":
                    result =  model.additionalElements(table);
                    break;
                case "Średnia":
                    result =  model.avgElements(table);

                    break;
                case "Min":
                    result =  model.minElements(table);

                    break;
                case "Max":
                    result =  model.maxElements(table);

                    break;
                default:
                    ShowMessageDialog("Błąd", "Wybierz operację z listy");
                    break;

            }


        }


        resultAreaAlert(result);

    }

    protected void resultAreaAlert(String desc){
        obszarWynikow.append(desc);
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

}