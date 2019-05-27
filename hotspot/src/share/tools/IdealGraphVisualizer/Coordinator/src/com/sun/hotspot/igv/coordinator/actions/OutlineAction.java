package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

public class OutlineAction extends AbstractAction {

    public OutlineAction() {
        super(NbBundle.getMessage(OutlineAction.class, "CTL_OutlineAction"));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = OutlineTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
