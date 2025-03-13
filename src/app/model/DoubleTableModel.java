package app.model;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class DoubleTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final int countRowTable = 5;
    private final int countColumnTable = 5;
    private Double[][] data = new Double[countRowTable][countColumnTable];
    private String[] colName = {"1","2","3","4","5"};

    public DoubleTableModel() {
        super();
        setZeroTable();
    }

    public int getRowCount() {
        return countRowTable;
    }
    public int getColumnCount() {
        return countColumnTable;
    }
    public String getColumnName(int col) {
        return colName[col];
    }
    public String[] getColumnNames() {
        return colName;
    }
    public Object getValueAt(int row, int col) {
        Object object = (Object) data[row][col];
        return object;
    }
    public Double[][] getDoubleValuesTable() {
        return data;
    }
    public Double getColumnSum(int col) {
        Double sum = new Double(0);
        for(int i=0; i<countRowTable; i++) {
            sum = sum + data[i][col];
        }
        return sum;
    }
    public String getStringValuesTable() {
        String str = "";
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                str = str + data[i][j] +" ";
            }
        return str;
    }
    public void setValue(JTextField jtf, JSlider j1, JSlider j2, JFrame f) {
        try {
            double value = Double.parseDouble(jtf.getText());
            int row = j1.getValue()-1;
            int col = j2.getValue()-1;
            data[row][col] = value;
            fireTableDataChanged();
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(
                    f,
                    "Błędnie wprowadzona liczba. Spróbuj ponownie",
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
            jtf.requestFocus();
            jtf.selectAll();
        }
    }
    public void setZeroTable() {
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                data[i][j] = new Double(0);
            }
        fireTableDataChanged();
    }
    public void setRandomTable() {
        Random random = new Random();
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                // ograniczenie znaku liczby i zakresu do 100
                data[i][j] = (double) Math.abs(random.nextInt()) % 100;
            }
        fireTableDataChanged();
    }
    public Double calculateSum() {
        Double sum = new Double(0);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                sum = sum + data[i][j];
            }
        return sum;
    }
    public Double calculateAverage() {
        Double avg = new Double(0.0);
        Double sum = calculateSum();
        if(sum > 0) avg = (sum)/(countRowTable*countColumnTable);
        return avg;
    }
    public Double calculateMax() {
        Double max = (double) Integer.MIN_VALUE;
        for (int i=0; i<countRowTable; i++)
            for (int j=0; j<countColumnTable; j++) {
                max = Math.max(max, data[i][j]);
            }
        return max;
    }
    public Double calculateMin() {
        Double min = (double) Integer.MAX_VALUE;
        for (int i=0; i<countRowTable; i++)
            for (int j=0; j<countColumnTable; j++) {
                min = Math.min(min, data[i][j]);
            }
        return min;
    }
}

