import javax.swing.*;

public class Toolbar extends JFrame {

    private JButton saveToolbar, aboutToolbar, printToolbar, openToolbar, addToolbar;
    private JButton removeToolbar, musicToolbar;
    private JButton sumToolbar, xToolbar, minToolbar, maxToolbar;
    private JButton helpToolbar;
    private JToolBar toolBar;

    private JButton setIcon(String iconName){
        return new JButton(new ImageIcon("grafika/" + iconName + ".jpg"));
    }

    public Toolbar(){
        toolBar = new JToolBar();
        saveToolbar = this.setIcon("save");
        aboutToolbar = this.setIcon("about");
        printToolbar = this.setIcon("print");
        openToolbar = this.setIcon("open");
        addToolbar = this.setIcon("add");

        removeToolbar= this.setIcon("remove");
        musicToolbar= this.setIcon("music");

        sumToolbar= this.setIcon("sum");
        xToolbar= this.setIcon("x");
        minToolbar= this.setIcon("min");
        maxToolbar= this.setIcon("max");

        helpToolbar= this.setIcon("help");
        aboutToolbar= this.setIcon("about");
    }
    public JToolBar getToolbar(){

        toolBar.add(saveToolbar);
        toolBar.add(printToolbar);
        toolBar.add(openToolbar);

        toolBar.addSeparator();

        toolBar.add(addToolbar);
        toolBar.add(removeToolbar);
        toolBar.add(musicToolbar);

        toolBar.addSeparator();

        toolBar.add(sumToolbar);
        toolBar.add(xToolbar);
        toolBar.add(minToolbar);
        toolBar.add(maxToolbar);

        toolBar.addSeparator();

        toolBar.add(helpToolbar);
        toolBar.add(aboutToolbar);

        return toolBar;
    }
}
