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
        setSize(400, 300);

        this.centerWindow();
        this.exitWindow();

        setJMenuBar(getMenu);


    }


    public static void main(String[] args) {
        // Utworzenie obiektu ramki głównej
        MainFrame frame = new MainFrame();

        // Ustawienie widoczności okna
        frame.setVisible(true);

        // dodanie toolbar
        frame.add(frame.Toolbar());
        frame.pack();
        frame.setVisible(true);
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

    public JToolBar Toolbar(){
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton("Dodaj"));
        toolBar.add(new JButton("Wyzeruj"));
        toolBar.add(new JButton("Wypełnij"));
        toolBar.addSeparator();

        toolBar.add(new JButton("Suma"));
        toolBar.add(new JButton("Średnia"));
        toolBar.add(new JButton("Min"));
        toolBar.add(new JButton("Max"));
        toolBar.addSeparator();

        toolBar.add(new JButton("Pomoc"));
        toolBar.add(new JButton("O programie"));

        return toolBar;
    }

}
