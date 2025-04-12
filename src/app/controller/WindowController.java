package app.controller;

import app.Window;
import app.model.CalculationModel;
import app.model.PieChartGenerator;
import app.model.SaveToFileModel;
import app.view.AboutWindow;
import app.view.HelpWindow;
import app.view.Menu;
import app.view.MessageDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowController extends Window implements ActionListener
{

    protected CalculationModel calculation = new CalculationModel();

    public WindowController(){
        myMenu = new Menu(this, myIcons);
        calculation.resetTable(table);
    }


    @Override
    public void actionPerformed(ActionEvent event) {


        String result;
        result = " ";

        if (event.getSource() == myMenu.saveMenuItem || event.getSource() == jbtSave || event.getSource() == addSave) {
            //            saveTableToFile(table);
            SaveToFileModel xd = new SaveToFileModel(table);

        }
        if (event.getSource() == myMenu.printMenuItem || event.getSource() == jbtPrint) {
            MessageDialog.Info("Drukowanie", "Plik został wydrukowany pomyślnie!");
        }
        if (event.getSource() == myMenu.exitMenuItem || event.getSource() == jbtExit) {
            closeWindow();
        }

        if (event.getSource() == jbtPieChart || event.getSource() == myMenu.piechartMenuitem) {
            PieChartGenerator pieChart = new PieChartGenerator(table);
            pieChart.showPieChart();
        }

        if (event.getSource() == myMenu.helpMenuItem || event.getSource() == jbtHelpContext) {
            HelpWindow PomocOkno = new HelpWindow();
            PomocOkno.setVisible(true);


        }
        if (event.getSource() == myMenu.aboutMenuItem || event.getSource() == jbtAbout) {

            AboutWindow aboutWindow = new AboutWindow(); // Tworzenie okna "Informacje o programie"
            aboutWindow.setVisible(true);

        }
        if (event.getSource() == myMenu.zoominMenuItem) {
            zoomOut();
        }
        if (event.getSource() == myMenu.zoomoutMenuItem) {
            zoomIn();
        }


        if (event.getSource() == addZeros) {
            result = calculation.resetTable(table);
        }
        if (event.getSource() == addValue) {


            try {
                int rowIndex = (int) jsRow.getValue();
                int colIndex = (int) jsCol.getValue();
                int value = Integer.parseInt(jtfValue.getText());
                result = calculation.setValueTable(rowIndex, colIndex, value, table);
            } catch (NumberFormatException ex) {
                MessageDialog.Error("Błąd", "Proszę wprowadzić poprawną liczbę całkowitą");
            }


        }

        if (event.getSource() == addFill) {
            try {
                int rowIndex = (int) jsRow.getValue();
                int colIndex = (int) jsCol.getValue();
                int value = Integer.parseInt(jtfValue.getText());
                result = calculation.fillTable(table, value);

            } catch (NumberFormatException ex) {
                MessageDialog.Error("Błąd", "Proszę wprowadzić poprawną liczbę całkowitą");
            }
        }

        if (event.getSource() == jbtSigma || event.getSource() == myMenu.addMenuItem) {
            result = calculation.additionalElements(table);
            System.out.print("dodwanbie" + result);
        }
        if (event.getSource() == jbtMean || event.getSource() == myMenu.meanMenuItem) {
            result = calculation.avgElements(table);
        }
        if (event.getSource() == jbtMin || event.getSource() == myMenu.minMenuItem) {
            result = calculation.minElements(table);
        }
        if (event.getSource() == jbtMax || event.getSource() == myMenu.maxMenuItem) {
            result = calculation.maxElements(table);
        }


        if (event.getSource() == calcBtn) {
            JComboBox<String> comboBox = (JComboBox<String>) ((JPanel) calcBtn.getParent()).getComponent(1);
            String selectedOperation = (String) comboBox.getSelectedItem();
            switch (selectedOperation) {
                case "Dodawanie":
                    result = calculation.additionalElements(table);
                    break;
                case "Średnia":
                    result = calculation.avgElements(table);

                    break;
                case "Min":
                    result = calculation.minElements(table);

                    break;
                case "Max":
                    result = calculation.maxElements(table);
                    break;
                default:
                    MessageDialog.Error("Błąd", "Wybierz operację z listy");
                    break;

            }


        }
        resultAreaPrint(result);

    }
}
