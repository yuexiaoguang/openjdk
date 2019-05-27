package com.sun.java.swing.ui;

import com.sun.java.swing.action.*;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// Referenced classes of package com.sun.java.swing.ui:
//            CommonUI
public class OkCancelButtonPanel extends JPanel
{

    public OkCancelButtonPanel(ActionListener listener)
    {
        DelegateAction okAction = new OkAction();
        okAction.addActionListener(listener);
        DelegateAction cancelAction = new CancelAction();
        cancelAction.addActionListener(listener);
        add(CommonUI.createButton(okAction));
        add(CommonUI.createButton(cancelAction));
    }

    public static final String OK_COMMAND = "ok-command";
    public static final String CANCEL_COMMAND = "cancel-command";

}
