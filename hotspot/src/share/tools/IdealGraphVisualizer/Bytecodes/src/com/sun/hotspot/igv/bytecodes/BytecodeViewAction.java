package com.sun.hotspot.igv.bytecodes;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

public class BytecodeViewAction extends AbstractAction {

    public BytecodeViewAction() {
        super(NbBundle.getMessage(BytecodeViewAction.class, "CTL_BytecodeViewAction"));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = BytecodeViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
