package app;


import javax.swing.*;

public class Main {
    public static final String APP_NAME = "JAKUB ACHTELIK - ZADANIE 1 PA";
    public static final int WIDTH_WINDOW = 800;
    public static final int HEIGHT_WINDOW = 480;

    public static void main(String[] args) {


        Window myWindow;
        myWindow = new Window();

        myWindow.setVisible(true);
        myWindow.setTitle(APP_NAME);
        myWindow.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        myWindow.setLocationRelativeTo(null);
        myWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



    }
}
