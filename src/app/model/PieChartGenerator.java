package app.model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChartGenerator {

    private JTable table;

    public PieChartGenerator(JTable table) {
        this.table = table;
    }

    public void showPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object value = table.getValueAt(row, col);
                if (value instanceof Number) {
                    dataset.setValue("W:" + row + "K:" + col, ((Number) value).doubleValue());
                }
            }
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Wykres Kołowy", dataset, true, true, false);

        showChartPanel(pieChart);
    }

    private void showChartPanel(JFreeChart chart) {
        JFrame frame = new JFrame("Wykres Kołowy");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}