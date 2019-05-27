package com.sun.hotspot.igv.controlflow;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

public class ControlFlowAction extends AbstractAction {

    public ControlFlowAction() {
        super(NbBundle.getMessage(ControlFlowAction.class, "CTL_ControlFlowAction"));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = ControlFlowTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
