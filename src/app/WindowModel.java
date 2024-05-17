package app;

import javax.swing.*;

public class WindowModel {



    public String resetTable(JTable table) {
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                table.setValueAt(0, wiersz, kolumna);
            }
        }
        return "Zerowanie tablicy \n";
    }

    public String setValueTable(
            int indeksWiersza, int indeksKolumny, int wartosc, JTextArea obszarWynikow, JTable table
    ) {

            table.setValueAt(wartosc, indeksWiersza-1, indeksKolumny-1);
            table.repaint();

            return "Ustawiono wartość " + wartosc + " w wierszu " + indeksWiersza + ", kolumnie " + indeksKolumny + "\n";

    }

    public String additionalElements(JTable table) {
        int suma = 0;
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                suma += (int) table.getValueAt(wiersz, kolumna);
            }
        }
        return "Suma wszystkich elementów: " + suma + "\n";
    }

    public String avgElements(JTable table) {
        int suma = 0;
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                suma += (int) table.getValueAt(wiersz, kolumna);
            }
        }
        double srednia = (double) suma / (table.getRowCount() * table.getColumnCount());
        return "Średnia wszystkich elementów: " + srednia + "\n";
    }

    public  String minElements(JTable table) {
        int min = Integer.MAX_VALUE;
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                int wartosc = (int) table.getValueAt(wiersz, kolumna);
                min = Math.min(min, wartosc);
            }
        }
        return "Wartość minimalna: " + min + "\n";
    }

    public String maxElements(JTable table) {
        int max = Integer.MIN_VALUE;
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                int wartosc = (int) table.getValueAt(wiersz, kolumna);
                max = Math.max(max, wartosc);
            }
        }
        return "Wartość maksymalna: " + max + "\n";
    }
}
