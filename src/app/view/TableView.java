package app.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableView {
    private JTable table;
    private JScrollPane scrollPane;

    public TableView() {
        initTable();
    }

    private void initTable() {
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

        scrollPane = new JScrollPane(table);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getTable() {
        return table;
    }
}
