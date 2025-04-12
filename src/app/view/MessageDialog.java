package app.view;

import javax.swing.*;

public class MessageDialog {

    // Statyczna metoda do wyświetlania komunikatu informacyjnego
    public static void Info(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Statyczna metoda do wyświetlania komunikatu o błędzie
    public static void Error(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}