import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final String APP_NAME = "PA - Zadanie 1 GUI";
    public MainFrame() {

        // menu, pobierz menu
        Menu menuItem = new Menu();
        JMenuBar getMenu = menuItem.createMenu();

        //  Ustawienie tytułu okna
        setTitle(APP_NAME);

        // Ustawienia rozmiaru okna
        setSize(900, 900);

        setResizable(true);
        setLocationRelativeTo(null);

        // Ustawienie widoczności okna
        setVisible(true);

        this.centerWindow();
        this.exitWindow();

        setJMenuBar(getMenu);

    }



    public static void main(String[] args) {
        // Utworzenie obiektu ramki głównej
        MainFrame frame = new MainFrame();
        Panels panels = new Panels();

        JPanel mainPanel = panels.createMainPanel();

        frame.add(mainPanel);
        frame.pack();


    }

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



}
