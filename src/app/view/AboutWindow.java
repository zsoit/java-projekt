package app.view;
import app.Config;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
/**
 * Program <code>RejestrLB</code>
 * Klasa <code>OProgramieOkno</code> definiujaca okno
 * z informacja o autorze.
 * @author
 * @version 1.0	15/12/2010
 */
public class AboutWindow extends JDialog implements ActionListener {

    private JLabel l0, l1, l2, l3, l4, l5, l6;
    private JLabel lBorder, lIcon;
    private JButton jBOk;
    private Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
    private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
    private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
    private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
    private Border line = null;

    /**
     * Konstruktor bezparametryczny klasy <code>OProgramieOkno</code>
     */
    public AboutWindow() {
        this.setTitle("Informacje o programie");
        this.setModal(true);
        this.setSize(370,265);
        this.setResizable(false);

        // obsluga zdarzenia okna
        this.addWindowListener	(new WindowAdapter(){
            // obsluga wcisniecia przycisku zamkniecia okna
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });

        // pobranie rozmiarow aplikacji
        Dimension dialogSize = getSize();
        // pobranie rozdzielczosci pulpitu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(dialogSize.height > screenSize.height)
            dialogSize.height = screenSize.height;
        if(dialogSize.width > screenSize.width)
            dialogSize.height = screenSize.width;

        // rozmieszczenie aplikacji na srodku ekranu
        setLocation((screenSize.width-dialogSize.width)/2,
                (screenSize.height-dialogSize.height)/2);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);

        try {
            lIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/pk.png")));
        }
        catch(Exception e) {
            lIcon = new JLabel();
        }
        l0 = new JLabel(Config.APP_NAME);
        l0.setFont(font1);
        l0.setHorizontalAlignment(SwingConstants.CENTER);
        l1 = new JLabel("Wersja: " + Config.VERSION);
        l1.setFont(font2);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l2 = new JLabel("Copyright (C) 2024 by");
        l2.setFont(font2);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l3 = new JLabel(Config.AUTHOR + " " + Config.STUDENT_ID);
        l3.setFont(font3);
        l3.setHorizontalAlignment(SwingConstants.CENTER);
        l4 = new JLabel("Politechnika Koszalińska - WEiI");
        l4.setFont(font3);
        l4.setHorizontalAlignment(SwingConstants.CENTER);
        l5 = new JLabel("e-mail: " + Config.EMAIL);
        l5.setFont(font4);
        l6 = new JLabel(Config.STUDIES_MAJOR);
        l6.setFont(font3);
        l6.setHorizontalAlignment(SwingConstants.CENTER);
        lBorder = new JLabel("");
        jBOk = new JButton("Ok");
        jBOk.addActionListener(this);
        line = new EtchedBorder(EtchedBorder.LOWERED);

        lIcon.setBounds(10,15,120,150);
        l0.setBounds(135,20,210,30);
        l1.setBounds(135,50,210,30);
        l2.setBounds(135,100,210,20);
        l3.setBounds(135,120,210,20);
        l4.setBounds(135,140,210,20);
        l6.setBounds(135,160,210,20);
        lBorder.setBounds(5,185,dialogSize.width-14,2);
        l5.setBounds(10,194,200,20);
        jBOk.setBounds(dialogSize.width-75,192,60,25);

        lBorder.setBorder(line);
        contentPane.add(l0);
        contentPane.add(l1);
        contentPane.add(l2);
        contentPane.add(l3);
        contentPane.add(l4);
        contentPane.add(l5);
        contentPane.add(l6);
        contentPane.add(lBorder);
        contentPane.add(jBOk);
        contentPane.add(lIcon);
    }
    /**
     * Publiczna metoda z interfejsu <code>ActionListener</code>
     * obslugujaca zdarzenie akcji <code>ActionEvent</code>
     */
    public void actionPerformed(ActionEvent eAE) {
        if(eAE.getSource() == jBOk) {
            setVisible(false);
        }
    }
}