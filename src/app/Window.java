package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame implements ActionListener  {
    //definicja zmiennych menu
    private JMenu fileMenu, helpMenu, editMenu, viewMenu, calcMenu;
    protected JMenuItem saveMenuItem, printMenuItem, exitMenuItem, aboutMenuItem, helpMenuItem, undoMenuItem, redoMenuItem, zoominMenuItem, zoomoutMenuItem, addMenuItem, meanMenuItem, minMenuItem, maxMenuItem;
    protected JButton jbtExit, jbtAbout, jbtHelpContext, jbtSave, jbtPrint, jbtSigma, jbtMean, jbtMin, jbtMax;
    protected JButton addValue, addZeros, addFill, addSave, obliczBtn;
    private StatusPanel statusPanel;

    private Icons myIcons = new Icons();

    protected JLabel labelValue, labelRow, labelCol;
    protected JTextField jtfValue;
    protected JSpinner jsRow, jsCol;
    private SpinnerNumberModel modelRow, modelCol;
    protected JTable table;

    private JPanel panelOblicz;

    protected JTextArea obszarWynikow;
    private JScrollPane przewijanieWynikow;
    private Font font;


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
            JOptionPane.showMessageDialog(this, "Błąd podczas wczytywania icon");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas tworzenia GUI");
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

    public JMenu createJMenu(String name, int keyEvent) {
        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(keyEvent);
        return jMenu;
    }

    public JMenuItem createJMenuItem(String name, Icon icon, KeyStroke key) {
        JMenuItem jMI;
        if (icon != null)
            jMI = new JMenuItem(name, icon);
        else jMI = new JMenuItem(name);
        jMI.setAccelerator(key);
        jMI.addActionListener(this);
        return jMI;
    }

    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jb = new JButton("", icon);
        jb.setToolTipText(tooltip);
        jb.addActionListener(this);
        return jb;
    }

    private void createMenus() {
        //utworzenie paska manu
        JMenuBar menuBar = new JMenuBar();
        //utworzenie pol menu glownego
        fileMenu = createJMenu("Plik", KeyEvent.VK_P);
        editMenu = createJMenu("Edycja", KeyEvent.VK_E);
        viewMenu = createJMenu("Widok", KeyEvent.VK_W);
        calcMenu = createJMenu("Obliczenia", KeyEvent.VK_O);
        helpMenu = createJMenu("Pomoc", KeyEvent.VK_C);


        //utworzenie menuitem
        saveMenuItem = createJMenuItem("Zapisz plik", myIcons.mIconSave, shortCuts(KeyEvent.VK_S));
        printMenuItem = createJMenuItem("Wydrukuj", myIcons.mIconPrint, shortCuts(KeyEvent.VK_P));
        exitMenuItem = createJMenuItem("Zamknij", myIcons.mIconExit, shortCuts(KeyEvent.VK_X));
        undoMenuItem = createJMenuItem("Cofnij", myIcons.mIconUndo, shortCuts(KeyEvent.VK_Z));
        redoMenuItem = createJMenuItem("Przywróć", myIcons.mIconRedo, shortCuts(KeyEvent.VK_Y));
        zoomoutMenuItem = createJMenuItem("Powiększ", myIcons.mIconZoomOut, shortCuts(KeyEvent.VK_U));
        zoominMenuItem = createJMenuItem("Pomniejsz", myIcons.mIconZoomIn, shortCuts(KeyEvent.VK_I));
        addMenuItem = createJMenuItem("Dodawanie", myIcons.mIconAdd, shortCuts(KeyEvent.VK_Q));
        meanMenuItem = createJMenuItem("Średnia", myIcons.mIconMean, shortCuts(KeyEvent.VK_W));
        minMenuItem = createJMenuItem("Min", myIcons.mIconMin, shortCuts(KeyEvent.VK_E));
        maxMenuItem = createJMenuItem("Max", myIcons.mIconMax, shortCuts(KeyEvent.VK_R));
        aboutMenuItem = createJMenuItem("O autorze", myIcons.mIconAbout, shortCuts(KeyEvent.VK_A));
        helpMenuItem = createJMenuItem("Kontekst pomocy", myIcons.mIconHelp, shortCuts(KeyEvent.VK_C));

        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);
        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        viewMenu.add(zoominMenuItem);
        viewMenu.add(zoomoutMenuItem);
        calcMenu.add(addMenuItem);
        calcMenu.add(meanMenuItem);
        calcMenu.add(minMenuItem);
        calcMenu.add(maxMenuItem);
        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(calcMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    public static KeyStroke shortCuts(int event){
       return  KeyStroke.getKeyStroke(event, ActionEvent.ALT_MASK);
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


    private WindowModel model = new WindowModel();


    @Override
    public void actionPerformed(ActionEvent event) {

        // TODO Auto-generated method stub

        String result = " ";

        if (event.getSource() == saveMenuItem || event.getSource() == jbtSave || event.getSource() == addSave) {
            ShowMessageDialog("Zapis", "Plik został zapisany pomyślnie!");
        }
        if (event.getSource() == printMenuItem || event.getSource() == jbtPrint) {
            ShowMessageDialog("Drukowanie", "Plik został wydrukowany pomyślnie!");
        }
        if (event.getSource() == exitMenuItem || event.getSource() == jbtExit) {
            closeWindow();
        }
        if (event.getSource() == helpMenuItem || event.getSource() == jbtHelpContext) {
            ShowMessageDialog("Kontekst pomocy", "Dokumentacja");
        }
        if (event.getSource() == aboutMenuItem || event.getSource() == jbtAbout) {
            ShowMessageDialog("Informacje o autorze", "Wersja: 1.0.8\nAutor: Jakub Achtelik\nKierunek: Informatyka.\nSemestr: IV.\nKontakt: achtelik.jakub@gmail.com");

        }
        if (event.getSource() == zoominMenuItem) {
            zoomOut();
        }
        if (event.getSource() == zoomoutMenuItem) {
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
        if (event.getSource() == jbtSigma || event.getSource() == addMenuItem || event.getSource() == addValue) {
            result =  model.additionalElements(table);
        }
        if (event.getSource() == jbtMean || event.getSource() == meanMenuItem) {
            result =  model.avgElements(table);
        }
        if (event.getSource() == jbtMin || event.getSource() == minMenuItem) {
            result = model.minElements(table);
        }
        if (event.getSource() == jbtMax || event.getSource() == maxMenuItem) {
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