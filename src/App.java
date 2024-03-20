import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.*;


//import com.jgoodies.forms.lay//import com.jgoodies.forms.layout.CellConstraints;
//import com.jgoodies.forms.layout.FormLayout;out.CellConstraints;
//import com.jgoodies.forms.layout.FormLayout;

public class App implements ActionListener {
    private JFrame frame; // utworzenie instancji okienka paska menu (pol menu i przedmiotow w menu)
    private JMenuBar myMenuBar;
    private JMenu file, edit, view, computation, help;
    private JButton zapisz, dodaj, wyzeruj, wypelnij, przycisk_w_dialogu;
    private JSlider wybierz_wiersz, wybierz_kolumne;
    private JMenuItem cut, copy, paste, selectAll, suma, srednia, min, max, zapisz_p, otworz, zamknij, panel_pomocy, info_o_programie;
    private JTextArea wpisanie_liczby, wynik_operacji;
    private JToolBar myToolbar;
    private JPanel myPanel;
    private JLabel wpiszLiczbe, wybierzFunkcje, wynik_info, wybrany_wiersz, wybrana_kolumna;
    private JScrollPane skrollWpisywanejLiczby, skrollOperacji, przewijanie_tabeli;
    private static JDialog myDialog;
    private Object[][] wartosci_w_komorkach;
    private String[] nazwy_kolumn = {"1","2","3","4","5"};
    private final JTable myTable;
    private DefaultListModel<String> l1;
    private Font czcionka;
    private JButton przycisk_menu1, przycisk_menu2, przycisk_menu3, przycisk_menu4, przycisk_menu5, przycisk_menu6, przycisk_menu7, przycisk_menu8, przycisk_menu9, przycisk_menu10, przycisk_menu11, przycisk_menu12;

    public App() { //konstruktor
        czcionka = new Font("Cambria", Font.BOLD, 14);
        frame = new JFrame("PA - projekt");
        frame.setSize(755,480);
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE); // dispose zamyka tylko te jedna ramke exit zamyka wszystkie ramki uruchomione w programie
        //frame.getContentPane().setLayout(null);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        // 4 do 6 przyciskow i separator w myToolbarze

        //----MENU----
        myMenuBar = new JMenuBar(); // utworzenie obiektow menu
        file = new JMenu("Plik");
        edit = new JMenu("Edycja");
        view = new JMenu("Widok");
        computation = new JMenu("Obliczenia");
        help = new JMenu("Pomoc");

        cut = new JMenuItem("Wytnij"); // przedmioty menu Edycja
        copy = new JMenuItem("Kopiuj");
        paste = new JMenuItem("Wklej");
        selectAll = new JMenuItem("Zaznacz wszystko");

        suma = new JMenuItem("Suma"); // przedmioty menu Obliczenia
        srednia = new JMenuItem("Średnia");
        min = new JMenuItem("Min");
        max = new JMenuItem("Max");

        //suma.setFont(czcionka);

        zapisz_p = new JMenuItem("Zapisz"); // przedmioty menu Plik
        otworz = new JMenuItem("Otwórz");
        zamknij = new JMenuItem("Zamknij");

        panel_pomocy = new JMenuItem("Panel pomocy"); // przedmioty menu Pomoc
        info_o_programie = new JMenuItem("O programie");

        cut.addActionListener(this); // umozliwienie wykonywania czynnosci przy kliknieciu w itemy menu
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        suma.addActionListener(this);
        srednia.addActionListener(this);
        min.addActionListener(this);
        max.addActionListener(this);

        zapisz_p.addActionListener(this);
        otworz.addActionListener(this);
        zamknij.addActionListener(this);

        panel_pomocy.addActionListener(this);
        info_o_programie.addActionListener(this);

        file.add(zapisz_p);
        file.add(otworz);
        file.add(zamknij);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        computation.add(suma);
        computation.add(srednia);
        computation.add(min);
        computation.add(max);

        myMenuBar.add(file);
        myMenuBar.add(edit);
        myMenuBar.add(view);
        myMenuBar.add(computation);
        myMenuBar.add(help);

        help.add(panel_pomocy);
        help.add(info_o_programie);


        myPanel = new JPanel(); // Glowny panel, na ktorym beda wszystkie informacje z zadania
        myPanel.setLayout(null);
        myPanel.add(myMenuBar);
        //myPanel.setLayout(new GridLayout());
        //myPanel.setPreferredSize(new Dimension(100,100));

        frame.setJMenuBar(myMenuBar);

        //----TOOLBAR----
        myToolbar = new JToolBar();
        myToolbar.setEnabled(false);
        myToolbar.setRollover(true);
        myToolbar.add(przycisk_menu1 = new JButton("Zapisz"));
        myToolbar.add(przycisk_menu2 = new JButton("Drukuj"));
        myToolbar.add(przycisk_menu3 = new JButton("Wyjdź"));
        myToolbar.addSeparator();
        myToolbar.add(przycisk_menu4 = new JButton("Dodaj"));
        myToolbar.add(przycisk_menu5 = new JButton("Wyzeruj"));
        myToolbar.add(przycisk_menu6 = new JButton("Wypełnij"));
        myToolbar.addSeparator();
        myToolbar.add(przycisk_menu7 = new JButton("Suma"));
        myToolbar.add(przycisk_menu8 = new JButton("Średnia"));
        myToolbar.add(przycisk_menu9 = new JButton("Min"));
        myToolbar.add(przycisk_menu10 = new JButton("Max"));
        myToolbar.addSeparator();
        myToolbar.add(przycisk_menu11 = new JButton("Pomoc"));
        myToolbar.add(przycisk_menu12 = new JButton("O programie"));

        przycisk_menu1.setFocusPainted(false);
        przycisk_menu2.setFocusPainted(false);
        przycisk_menu3.setFocusPainted(false);
        przycisk_menu4.setFocusPainted(false);
        przycisk_menu5.setFocusPainted(false);
        przycisk_menu6.setFocusPainted(false);
        przycisk_menu7.setFocusPainted(false);
        przycisk_menu8.setFocusPainted(false);
        przycisk_menu9.setFocusPainted(false);
        przycisk_menu10.setFocusPainted(false);
        przycisk_menu11.setFocusPainted(false);
        przycisk_menu12.setFocusPainted(false);

        przycisk_menu1.addActionListener(this);
        przycisk_menu2.addActionListener(this);
        przycisk_menu3.addActionListener(this);
        przycisk_menu4.addActionListener(this);
        przycisk_menu5.addActionListener(this);
        przycisk_menu6.addActionListener(this);
        przycisk_menu7.addActionListener(this);
        przycisk_menu8.addActionListener(this);
        przycisk_menu9.addActionListener(this);
        przycisk_menu10.addActionListener(this);
        przycisk_menu11.addActionListener(this);
        przycisk_menu12.addActionListener(this);

        //----WLASCIWA CZESC APLIKACJI----
        wpiszLiczbe = new JLabel("Wprowadzana liczba:");
        wpiszLiczbe.setBounds(10,10,220,20);
        myPanel.add(wpiszLiczbe);

        wpisanie_liczby = new JTextArea(); // wprowadzanie danych do tabeli
        //wpisanie_liczby.setBounds(130,10,80,20);
        wpisanie_liczby.setLineWrap(true);
        wpisanie_liczby.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        skrollWpisywanejLiczby = new JScrollPane(wpisanie_liczby);
        skrollWpisywanejLiczby.setBounds(135,10,90,20);
        skrollWpisywanejLiczby.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myPanel.add(skrollWpisywanejLiczby);

        myToolbar.add(przycisk_menu4 = new JButton("Dodaj"));
        myToolbar.add(przycisk_menu5 = new JButton("Wyzeruj"));
        myToolbar.add(przycisk_menu6 = new JButton("Wypełnij"));
        myToolbar.addSeparator();
        myToolbar.add(przycisk_menu7 = new JButton("Suma"));
        myToolbar.add(przycisk_menu8 = new JButton("Średnia"));
        myToolbar.add(przycisk_menu9 = new JButton("Min"));
        myToolbar.add(przycisk_menu10 = new JButton("Max"));
        myToolbar.addSeparator();
        myToolbar.add(przycisk_menu11 = new JButton("Pomoc"));
        myToolbar.add(przycisk_menu12 = new JButton("O programie"));
        wybrana_kolumna = new JLabel("Wybrana kolumna:");
        wybrana_kolumna.setBounds(270,10,220,20);
        myPanel.add(wybrana_kolumna);

        wybierz_kolumne = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
        wybierz_kolumne.setPaintTicks(true);
        wybierz_kolumne.setMajorTickSpacing(1);
        wybierz_kolumne.setSnapToTicks(true);
        wybierz_kolumne.setBounds(380,10,100,20);

        wybrany_wiersz = new JLabel("Wybrany wiersz:");
        wybrany_wiersz.setBounds(530,10,220,20);
        myPanel.add(wybrany_wiersz);

        wybierz_wiersz = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
        wybierz_wiersz.setPaintTicks(true);
        wybierz_wiersz.setMajorTickSpacing(1);
        wybierz_wiersz.setSnapToTicks(true);
        wybierz_wiersz.setBounds(630,10,100,20);

        myPanel.add(wybierz_kolumne);
        myPanel.add(wybierz_wiersz);

        //----TABELKA----
        wartosci_w_komorkach = new Object[5][5];
        myTable = new JTable(wartosci_w_komorkach,nazwy_kolumn);
        myTable.getTableHeader().setReorderingAllowed(false); // uniemożliwienie przemieszczania kolumn w tabeli
        przewijanie_tabeli = new JScrollPane(myTable);
        przewijanie_tabeli.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        przewijanie_tabeli.setBounds(10,50,600,110);

        myPanel.add(przewijanie_tabeli);

        //----FUNKCJE DO TABELKI (PRZYCISKI)----
        dodaj = new JButton("Dodaj");
        wyzeruj = new JButton("Wyzeruj");
        wypelnij = new JButton("Wypełnij");
        zapisz = new JButton("Zapisz");

        dodaj.setBounds(630, 50, 100, 30);
        wyzeruj.setBounds(630, 85, 100, 30);
        wypelnij.setBounds(630, 120, 100, 30);
        zapisz.setBounds(630, 155, 100, 30);

        dodaj.setFocusPainted(false);
        wyzeruj.setFocusPainted(false);
        wypelnij.setFocusPainted(false);
        zapisz.setFocusPainted(false);

        dodaj.addActionListener(this);
        wyzeruj.addActionListener(this);
        wypelnij.addActionListener(this);
        zapisz.addActionListener(this);

        //dodaj.setFont(czcionka);
        //wyzeruj.setFont(czcionka);
        //wypelnij.setFont(czcionka);
        //zapisz.setFont(czcionka);

        myPanel.add(dodaj);
        myPanel.add(wyzeruj);
        myPanel.add(wypelnij);
        myPanel.add(zapisz);

        // myToolbar do 2/3 ekranu (gosc ma 16 na 16)

        //----LISTA - FUNKCJE OPERUJACE NA DANYCH Z TABELI----
        wybierzFunkcje = new JLabel("Wybrana operacja");
        wybierzFunkcje.setBounds(10,155,120,40);
        myPanel.add(wybierzFunkcje);
        l1 = new DefaultListModel<>();
        l1.addElement("Suma elementów");
        l1.addElement("Średnia elementów");
        l1.addElement("Minimum");
        l1.addElement("Maksimum");
        JList<String> list = new JList<>(l1);
        list.setBounds(10,190, 120,75);
        myPanel.add(list);

        // ----REZULTATY OPERACJI----
        wynik_info = new JLabel("Wynik operacji");
        wynik_info.setBounds(340,265,120,20);
        wynik_operacji = new JTextArea();
        //wynik_operacji.setBounds(10,280,720,80);
        wynik_operacji.setLineWrap(true);
        wynik_operacji.setEditable(false);
        skrollOperacji = new JScrollPane(wynik_operacji); // nie dziala
        skrollOperacji.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        skrollOperacji.setBounds(10, 290, 720, 80); // JScrollPane musi miec ustawiony rozmiar
        myPanel.add(wynik_info);
        myPanel.add(skrollOperacji);

        frame.getContentPane().add(myToolbar, BorderLayout.NORTH); // dodajemy do kontenera (wyluskanego z glownej ramki)...
        // ...myToolbar z orientacja na polnoc

        //----pop up okienko z info o autorze----
        myDialog = new JDialog(frame, "Projekt 1 - GUI", true);
        myDialog.setLayout(new FlowLayout());
        myDialog.setResizable(false);
        myDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        przycisk_w_dialogu = new JButton("OK");
        przycisk_w_dialogu.addActionListener(this);
        przycisk_w_dialogu.setFocusPainted(false);

        myDialog.add( new JLabel ("Aplikację wykonał: Budnik Oliwier (U-20041)"));
        myDialog.add(przycisk_w_dialogu);
        myDialog.setSize(300,100);
        myDialog.setLocationRelativeTo(null);
        frame.add(myPanel);
        //frame.getContentPane().add(skrollOperacji, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    //---OBSLUGA WSZYSTKICH WYDARZEN W APLIKACJI----
    @Override
    public void actionPerformed(ActionEvent e) { // przechwycenie sygnalu klikniecia w jakikolwiek przycisk
        if(e.getSource()==cut) // getSource() zwraca obiekt na ktorym zostalo zarejestrowane wydarzenie (event) np. klikniecie w przycisk
            wpisanie_liczby.cut(); // dzieki takiemu rozwiazaniu nie trzeba korzystac z klas anonimowych
        else if(e.getSource()==paste)
            wpisanie_liczby.paste();
        else if(e.getSource()==copy)
            wpisanie_liczby.copy();
        else if(e.getSource()==selectAll)
            wpisanie_liczby.selectAll();
        else if(e.getSource()==zamknij)
            System.exit(0);
        else if(e.getSource()==info_o_programie)
            myDialog.setVisible(true);
        else if(e.getSource()==przycisk_w_dialogu)
            myDialog.setVisible(false);
        else if(e.getSource()==przycisk_menu3)
            System.exit(0);
        else if(e.getSource()==przycisk_menu12)
            myDialog.setVisible(true);
    }
    //----LAUNCHER----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}