package app;

import javax.swing.*;
import java.net.URL;

public class Icons {



    private static final String ICON_PATH = "/resources/";


    public Icon mIconSave, mIconPrint, iconExit, mIconExit, iconAbout, mIconAbout, iconHelp, mIconHelp, mIconUndo, mIconRedo, mIconZoomIn, mIconZoomOut, mIconAdd, mIconMean, mIconMin, mIconMax, iconSave, iconPrint, iconSigma, iconMean, iconMin, iconMax;
    public Icon iconAdding, iconZero, iconFill, iconSave_Doc;

    public void createIcons() throws IconException {

        // utworzenie ikon 24 * 24 px dla paska toolbar
        iconExit = createMyIcon("exit.png");
        iconAbout = createMyIcon("about.png");
        iconHelp = createMyIcon("help.png");
        iconSave = createMyIcon("save.png");
        iconPrint = createMyIcon("print.png");
        iconSigma = createMyIcon("sigma.png");
        iconMean = createMyIcon("mean.png");
        iconMin = createMyIcon("min.png");
        iconMax = createMyIcon("max.png");
        iconAdding = createMyIcon("add.png");
        iconZero = createMyIcon("zero.png");
        iconFill = createMyIcon("fill.png");
        iconSave_Doc = createMyIcon("save_document.png");

        // utworzenie ikon 16 * 16 px dla paska menu
        mIconExit = createMyIcon("mexit.png");
        mIconAbout = createMyIcon("mabout.png");
        mIconHelp = createMyIcon("mhelp.png");
        mIconSave = createMyIcon("msave.png");
        mIconPrint = createMyIcon("mprint.png");
        mIconUndo = createMyIcon("undo.png");
        mIconRedo = createMyIcon("redo.png");
        mIconZoomIn = createMyIcon("zoom_in.png");
        mIconZoomOut = createMyIcon("zoom_out.png");
        mIconAdd = createMyIcon("msigma.png");
        mIconMean = createMyIcon("mmean.png");
        mIconMin = createMyIcon("mmin.png");
        mIconMax = createMyIcon("mmax.png");

    }

    public Icon createMyIcon(String nameFile) throws IconException {
        String name = ICON_PATH + nameFile;
        Icon icon = null;
        URL url = getClass().getResource(name);
        if (url == null) throw new IconException();
        else icon = new ImageIcon(url);

        return icon;
    }


}
