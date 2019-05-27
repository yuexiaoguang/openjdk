package com.sun.hotspot.igv.filterwindow;

import javax.swing.JList;
import org.openide.explorer.view.ListView;
import org.openide.explorer.view.NodeListModel;

public class CheckListView extends ListView {

    @Override
    public void showSelection(int[] indices) {
        super.showSelection(indices);
    }

    @Override
    protected NodeListModel createModel() {
        return new CheckNodeListModel();
    }

    @Override
    protected JList createList() {
        JList tmpList = super.createList();
        tmpList.setCellRenderer(new CheckRenderer(tmpList));
        return tmpList;
    }
}
