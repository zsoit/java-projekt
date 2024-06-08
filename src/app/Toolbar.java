package app;


import javax.swing.*;
import java.awt.event.ActionListener;


public class Toolbar {
    public  JButton jbtExit, jbtAbout, jbtHelpContext, jbtSave, jbtPrint, jbtSigma, jbtMean, jbtMin, jbtMax;

    private ActionListener listener;
    private Icons myIcons;

    public Toolbar(ActionListener listener, Icons icons) {
        this.listener = listener;
        this.myIcons = icons;
    }

    public JToolBar getToolBar() {
        JToolBar jbt = new JToolBar(JToolBar.HORIZONTAL);
        jbt.setFloatable(false);

        jbtSave = createJButtonToolBar("Zapis pliku", myIcons.iconSave);
        jbtPrint = createJButtonToolBar("Drukowanie", myIcons.iconPrint);
        jbtExit = createJButtonToolBar("Zamknięcie aplikacji", myIcons.iconExit);
        jbtSigma = createJButtonToolBar("Dodawanie", myIcons.iconSigma);
        jbtMean = createJButtonToolBar("Średnia", myIcons.iconMean);
        jbtMin = createJButtonToolBar("Minimum", myIcons.iconMin);
        jbtMax = createJButtonToolBar("Maximum", myIcons.iconMax);
        jbtHelpContext = createJButtonToolBar("Kontekst pomocy", myIcons.iconHelp);
        jbtAbout = createJButtonToolBar("Informacje o autorze", myIcons.iconAbout);

        jbt.add(Box.createHorizontalStrut(5));
        jbt.add(jbtSave);
        jbt.add(jbtPrint);
        jbt.add(jbtExit);
        jbt.addSeparator();
        jbt.add(jbtSigma);
        jbt.add(jbtMean);
        jbt.add(jbtMin);
        jbt.add(jbtMax);
        jbt.addSeparator();
        jbt.add(jbtHelpContext);
        jbt.add(jbtAbout);

        return jbt;
    }

    public JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jb;

        if (icon != null)
            jb = new JButton(icon);
        else
            jb = new JButton();

        jb.setToolTipText(tooltip);
        jb.addActionListener(listener);
        return jb;
    }

}
