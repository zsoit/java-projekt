import javax.swing.*;

public class Toolbar extends JFrame {

    private Toolbar toolBar;
    private JButton addBtn, zeroBtn, insertBtn, sumBtn, avgBtn, minBtn, maxBtn, helpBtn, aboutBtn;

    public JToolBar getToolbar(){
        toolBar.add(new JButton("Dodaj"));
        toolBar.add(new JButton("Wyzeruj"));
        toolBar.add(new JButton("Wypełnij"));
//        toolBar.addSeparator();

        toolBar.add(new JButton("Suma"));
        toolBar.add(new JButton("Średnia"));
        toolBar.add(new JButton("Min"));
        toolBar.add(new JButton("Max"));
        //toolBar.addSeparator();

        toolBar.add(new JButton("Pomoc"));
        toolBar.add(new JButton("O programie"));

        return toolBar.getToolbar();
    }
}
