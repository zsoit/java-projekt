package app;


import app.view.Tips;


public class Main {


    public static void main(String[] args) {


        // Wyświetlenie okna
        Window myWindow = new Window();
        myWindow.WindowMainSetup();

        // Wyświetlenie okienka z podpowiedziami
        Tips tips = new Tips();
        tips.showTips();


    }
}
