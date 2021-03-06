package com.sun.hotspot.igv.filterwindow.actions;

import com.sun.hotspot.igv.filterwindow.FilterTopComponent;
import com.sun.hotspot.igv.filter.Filter;
import javax.swing.Action;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

public final class MoveFilterDownAction extends CookieAction {

    protected void performAction(Node[] activatedNodes) {
        for (Node n : activatedNodes) {
            Filter c = n.getLookup().lookup(Filter.class);
            FilterTopComponent.findInstance().getSequence().moveFilterDown(c);
        }
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    public MoveFilterDownAction() {

        putValue(Action.SHORT_DESCRIPTION, "Move filter downwards");
    }

    public String getName() {
        return NbBundle.getMessage(MoveFilterUpAction.class, "CTL_MoveFilterDownAction");
    }

    protected Class[] cookieClasses() {
        return new Class[]{
            Filter.class
        };
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/filterwindow/images/down.gif";
    }

    @Override
    protected void initialize() {
        super.initialize();
        putValue("noIconInMenu", Boolean.TRUE);
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
