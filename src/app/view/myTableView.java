package app.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class myTableView {

    private JTable table;

    public JTable getTable() {
        return table;
    }

    public myTableView() {
        this.setTable();
    }
    public void setTable() {
        table = new JTable(5,5);
        table.setEnabled(false);
        table.setRowHeight(table.getRowHeight() + 11);
        //table.setBackground(new Color(211, 211, 211));

        // Ustawianie koloru tła i koloru czcionki
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);

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
}
