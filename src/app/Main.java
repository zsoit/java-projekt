package app;


import app.controller.WindowController;
import app.view.Tips;


public class Main {


    public static void main(String[] args) {


        // Wyświetlenie okna
//        app.Window myWindow = new app.Window();
//        myWindow.WindowMainSetup();

        WindowController wc = new WindowController();
        wc.WindowMainSetup();

        // Wyświetlenie okienka z podpowiedziami
        Tips tips = new Tips();
        tips.showTips();


    }
}
