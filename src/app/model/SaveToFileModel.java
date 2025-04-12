package app.model;

import app.view.MessageDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFileModel extends Component {
    private static String defaultFilePath = null;

    public SaveToFileModel(JTable table) {
        // Jeśli nie ma domyślnej ścieżki, poproś użytkownika o wybór lokalizacji pliku
        if (defaultFilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Wybierz lokalizację pliku");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                defaultFilePath = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
            } else {
                MessageDialog.Info("Zapis", "Anulowano zapis do pliku");
                return;
            }
        }

        try {
            FileWriter fw = new FileWriter(defaultFilePath);
            BufferedWriter bw = new BufferedWriter(fw);

            TableModel model = table.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            // Zapisz nagłówki kolumn
            for (int i = 0; i < colCount; i++) {
                bw.write(model.getColumnName(i));
                if (i < colCount - 1)
                    bw.write("\t"); // dodajemy tabulator między nagłówkami kolumn
            }
            bw.newLine(); // nowa linia po nagłówkach

            // Zapisz dane
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = model.getValueAt(row, col);
                    // Sprawdź czy wartość jest null, jeśli tak, zapisz pusty ciąg znaków
                    if (value != null)
                        bw.write(value.toString());
                    if (col < colCount - 1)
                        bw.write("\t"); // dodajemy tabulator między komórkami
                }
                bw.newLine(); // nowa linia po zapisaniu wszystkich danych w wierszu
            }

            bw.close();
            fw.close();
            MessageDialog.Info("Zapis", "Plik został zapisany pomyślnie");
        } catch (IOException e) {
            MessageDialog.Error("Błąd", "Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());

        }
    }
}
