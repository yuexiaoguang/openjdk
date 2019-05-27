package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.OutlineTopComponent;
import com.sun.hotspot.igv.data.services.GroupOrganizer;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.CallableSystemAction;

public class StructuredViewAction extends CallableSystemAction {

    private static JButton dropDownButton;
    private static ButtonGroup buttonGroup;
    private static JPopupMenu popup;
    private MyMenuItemListener menuItemListener;
    private Map<JMenuItem, GroupOrganizer> map;

    public StructuredViewAction() {

        putValue(Action.SHORT_DESCRIPTION, "Cluster nodes into blocks");
    }

    @Override
    public Component getToolbarPresenter() {

        Image iconImage = Utilities.loadImage("com/sun/hotspot/igv/coordinator/images/structure.gif");
        ImageIcon icon = new ImageIcon(iconImage);

        popup = new JPopupMenu();

        menuItemListener = new MyMenuItemListener();

        buttonGroup = new ButtonGroup();

        Collection<? extends GroupOrganizer> organizersCollection = Lookup.getDefault().lookupAll(GroupOrganizer.class);

        List<GroupOrganizer> organizers = new ArrayList<GroupOrganizer>(organizersCollection);
        Collections.sort(organizers, new Comparator<GroupOrganizer>() {
            public int compare(GroupOrganizer a, GroupOrganizer b) {
                return a.getName().compareTo(b.getName());
            }
        });

        map = new HashMap<JMenuItem, GroupOrganizer>();

        boolean first = true;
        for(GroupOrganizer organizer : organizers) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(organizer.getName());
            map.put(item, organizer);
            item.addActionListener(menuItemListener);
            buttonGroup.add(item);
            popup.add(item);
            if(first) {
                item.setSelected(true);
                first = false;
            }
        }

        dropDownButton = DropDownButtonFactory.createDropDownButton(
                new ImageIcon(
                new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_GRAY)),
                popup);

        dropDownButton.setIcon(icon);

        dropDownButton.setToolTipText("Insert Layer Registration");

        dropDownButton.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    performAction();
                }
            }
        });

        dropDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction();
            }
        });

        popup.addPopupMenuListener(new PopupMenuListener() {

            public void popupMenuCanceled(PopupMenuEvent e) {
                dropDownButton.setSelected(false);
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                dropDownButton.setSelected(false);
            }

            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                dropDownButton.setSelected(true);
            }
        });

        return dropDownButton;

    }

    private class MyMenuItemListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            JMenuItem item = (JMenuItem) ev.getSource();
            GroupOrganizer organizer = map.get(item);
            assert organizer != null : "Organizer must exist!";
            OutlineTopComponent.findInstance().setOrganizer(organizer);
        }
    }


    @Override
    public void performAction() {
        popup.show(dropDownButton, 0, dropDownButton.getHeight());
    }

    public String getName() {
        return "Structured View";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

}
