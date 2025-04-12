package app.model;

import javax.swing.*;
import java.util.Random;

public class CalculationModel {
    public String resetTable(JTable table) {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                table.setValueAt(0, row, col);
            }
        }
        return "Zerowanie tablicy \n";
    }

    public String fillTable(JTable table, int myValue) {
        Random random = new Random();
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                int randomNumber = random.nextInt(100); // Zakres od 0 do 99, możesz dostosować według potrzeb
                table.setValueAt(randomNumber, row, col);
            }
        }
        return "Wypełnione losowymi liczbami.\n";
    }


    public String setValueTable(
            int rowIndex, int colIndex, int value, JTable table
    ) {

            table.setValueAt(value, rowIndex-1, colIndex-1);
            table.repaint();

            return "Ustawiono wartość " + value + " w wierszu " + rowIndex + ", kolumnie " + colIndex + "\n";

    }


    public String additionalElements(JTable table) {
        int sum = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                sum += (int) table.getValueAt(row, col);
            }
        }


        return "Suma wszystkich elementów: " + sum + "\n";
    }

    public String avgElements(JTable table) {
        int sum = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                sum += (int) table.getValueAt(row, col);
            }
        }

        double srednia = (double) sum / (table.getRowCount() * table.getColumnCount());
        return "Średnia wszystkich elementów: " + srednia + "\n";
    }

    public  String minElements(JTable table) {
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                int value = (int) table.getValueAt(row, col);
                min = Math.min(min, value);
            }
        }
        return "Wartość minimalna: " + min + "\n";
    }

    public String maxElements(JTable table) {
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                int value = (int) table.getValueAt(row, col);
                max = Math.max(max, value);
            }
        }
        return "Wartość maksymalna: " + max + "\n";
    }


}


