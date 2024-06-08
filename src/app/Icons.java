package app;

import javax.swing.*;
import java.net.URL;

public class Icons {

    private static final String ICON_PATH = "/icons/";

    public Icon mIconSave, mIconPrint, iconExit, mIconExit, iconAbout, mIconAbout, iconHelp, mIconHelp, mIconUndo, mIconRedo, mIconZoomIn, mIconZoomOut, mIconAdd, mIconMean, mIconMin, mIconMax, iconSave, iconPrint, iconSigma, iconMean, iconMin, iconMax;
    public Icon iconAdding, iconZero, iconFill, iconSave_Doc;

    public void createIcons() throws IconException {

        // 24x24 px dla paska toolbar
        iconExit = createMyIcon("/24x24_toolbar/exit.png");
        iconAbout = createMyIcon("/24x24_toolbar/about.png");
        iconHelp = createMyIcon("/24x24_toolbar/help.png");
        iconSave = createMyIcon("/24x24_toolbar/save.png");
        iconPrint = createMyIcon("/24x24_toolbar/print.png");
        iconSigma = createMyIcon("/24x24_toolbar/sigma.png");
        iconMean = createMyIcon("/24x24_toolbar/addition.png");
        iconMin = createMyIcon("/24x24_toolbar/min.png");
        iconMax = createMyIcon("/24x24_toolbar/max.png");

        // 16x16 dla przyciskow
        iconAdding = createMyIcon("/16x16_btn/add.png");
        iconZero = createMyIcon("/16x16_btn/zero.png");
        iconFill = createMyIcon("/16x16_btn/fill.png");
        iconSave_Doc = createMyIcon("/16x16_btn/save_document.png");

        // 16x16 px dla paska menu
        mIconExit = createMyIcon("/16x16_toolbar/exit.png");
        mIconAbout = createMyIcon("/16x16_toolbar/about.png");
        mIconHelp = createMyIcon("/16x16_toolbar/help.png");
        mIconSave = createMyIcon("/16x16_toolbar/save.png");
        mIconPrint = createMyIcon("/16x16_toolbar/print.png");
        mIconUndo = createMyIcon("/16x16_toolbar/undo.png");
        mIconRedo = createMyIcon("/16x16_toolbar/redo.png");
        mIconZoomIn = createMyIcon("/16x16_toolbar/zoom_in.png");
        mIconZoomOut = createMyIcon("/16x16_toolbar/zoom_out.png");
        mIconAdd = createMyIcon("/16x16_toolbar/sigma.png");
        mIconMean = createMyIcon("/16x16_toolbar/addition.png");
        mIconMin = createMyIcon("/16x16_toolbar/min.png");
        mIconMax = createMyIcon("/16x16_toolbar/max.png");

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
