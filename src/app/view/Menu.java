package app.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Menu {
    public JMenu fileMenu, helpMenu, editMenu, viewMenu, calcMenu;
    public  JMenuItem saveMenuItem, printMenuItem, exitMenuItem, aboutMenuItem, helpMenuItem, undoMenuItem, redoMenuItem, zoominMenuItem, zoomoutMenuItem, addMenuItem, meanMenuItem, minMenuItem, maxMenuItem, piechartMenuitem;

    private Icons myIcons;
    private ActionListener listener;

    public Menu(ActionListener listener, Icons icons) {
        this.listener = listener;
        this.myIcons = icons;
    }

    public  JMenuBar getMenu() {
        //utworzenie paska manu
        JMenuBar menuBar = new JMenuBar();
        //utworzenie pol menu glownego
        fileMenu = createJMenu("Plik", KeyEvent.VK_P);
        editMenu = createJMenu("Edycja", KeyEvent.VK_E);
        viewMenu = createJMenu("Widok", KeyEvent.VK_W);
        calcMenu = createJMenu("Obliczenia", KeyEvent.VK_O);
        helpMenu = createJMenu("Pomoc", KeyEvent.VK_C);


        //utworzenie menuitem
        saveMenuItem = createJMenuItem("Zapisz plik", myIcons.mIconSave, shortCuts(KeyEvent.VK_S));
        printMenuItem = createJMenuItem("Wydrukuj", myIcons.mIconPrint, shortCuts(KeyEvent.VK_P));
        exitMenuItem = createJMenuItem("Zamknij", myIcons.mIconExit, shortCuts(KeyEvent.VK_X));
        undoMenuItem = createJMenuItem("Cofnij", myIcons.mIconUndo, shortCuts(KeyEvent.VK_Z));
        redoMenuItem = createJMenuItem("Przywróć", myIcons.mIconRedo, shortCuts(KeyEvent.VK_Y));
        zoomoutMenuItem = createJMenuItem("Powiększ", myIcons.mIconZoomOut, shortCuts(KeyEvent.VK_U));
        zoominMenuItem = createJMenuItem("Pomniejsz", myIcons.mIconZoomIn, shortCuts(KeyEvent.VK_I));
        addMenuItem = createJMenuItem("Dodawanie", myIcons.mIconAdd, shortCuts(KeyEvent.VK_Q));
        meanMenuItem = createJMenuItem("Średnia", myIcons.mIconMean, shortCuts(KeyEvent.VK_W));
        minMenuItem = createJMenuItem("Min", myIcons.mIconMin, shortCuts(KeyEvent.VK_E));
        maxMenuItem = createJMenuItem("Max", myIcons.mIconMax, shortCuts(KeyEvent.VK_R));
        piechartMenuitem = createJMenuItem("Wykres kołowy", myIcons.iconPieChart, shortCuts(KeyEvent.VK_T));
        aboutMenuItem = createJMenuItem("O autorze", myIcons.mIconAbout, shortCuts(KeyEvent.VK_A));
        helpMenuItem = createJMenuItem("Kontekst pomocy", myIcons.mIconHelp, shortCuts(KeyEvent.VK_C));

        calcMenu.add(piechartMenuitem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);
        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        viewMenu.add(zoominMenuItem);
        viewMenu.add(zoomoutMenuItem);
        calcMenu.add(addMenuItem);
        calcMenu.add(meanMenuItem);
        calcMenu.add(minMenuItem);
        calcMenu.add(maxMenuItem);
        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(calcMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    public JMenu createJMenu(String name, int keyEvent) {
        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(keyEvent);
        return jMenu;
    }

    public JMenuItem createJMenuItem(String name, Icon icon, KeyStroke key) {
        JMenuItem jMI;
        if (icon != null)
            jMI = new JMenuItem(name, icon);
        else jMI = new JMenuItem(name);
        jMI.setAccelerator(key);
        jMI.addActionListener(listener);
        return jMI;
    }

    public static KeyStroke shortCuts(int event){
        return  KeyStroke.getKeyStroke(event, ActionEvent.ALT_MASK);
    }
}
