import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public void centerWindow(){
        // Pobranie rozmiaru ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Obliczenie położenia okna na środku ekranu
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

        // Ustawienie położenia okna
        setLocation(x, y);
    }
    public void exitWindow(){
        // Dodanie obsługi zdarzenia zamknięcia okna
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0); // Zamknięcie aplikacji przy zamknięciu okna
            }
        });

    }
    public MainFrame() {

        String APPNAME = "PA - Zadanie 1 GUI";

        // menu
        Menu menuItem = new Menu();

        //  Ustawienie tytułu okna
        setTitle(APPNAME);

        // Ustawienia rozmiaru okna
        setSize(400, 300);

        this.centerWindow();
        this.exitWindow();

        setJMenuBar(menuItem.createMenu());
        menuItem.Events();

    }

    public static void main(String[] args) {
        // Utworzenie obiektu ramki głównej
        MainFrame frame = new MainFrame();

        // Ustawienie widoczności okna
        frame.setVisible(true);
    }
}
