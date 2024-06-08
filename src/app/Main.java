package app;


import javax.swing.*;

public class Main {


    public static void main(String[] args) {


        Window myWindow;
        myWindow = new Window();

        myWindow.setVisible(true);
        myWindow.setTitle(Config.AUTHOR + " " + Config.STUDENT_ID + " - " + Config.APP_NAME);
        myWindow.setSize(Config.WIDTH_WINDOW, Config.HEIGHT_WINDOW);
        myWindow.setLocationRelativeTo(null);
        myWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Wyświetlenie okienka z podpowiedziami
        Tips tips = new Tips();
        tips.showTips();


    }
}
