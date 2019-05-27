package com.sun.hotspot.igv.settings;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public final class ViewOptionsCategory extends OptionsCategory {

    @Override
    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("com/sun/hotspot/igv/settings/settings.gif"));
    }

    public String getCategoryName() {
        return NbBundle.getMessage(ViewOptionsCategory.class, "OptionsCategory_Name_View");
    }

    public String getTitle() {
        return NbBundle.getMessage(ViewOptionsCategory.class, "OptionsCategory_Title_View");
    }

    public OptionsPanelController create() {
        return new ViewOptionsPanelController();
    }
}
