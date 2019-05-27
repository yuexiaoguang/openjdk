package com.sun.hotspot.igv.filterwindow;

import org.openide.explorer.view.NodeListModel;
import org.openide.nodes.Node;

public class CheckNodeListModel extends NodeListModel {

    private Node rootNode;

    @Override
    public void setNode(Node rootNode) {
        this.rootNode = rootNode;
        super.setNode(rootNode);
    }

    public CheckNode getCheckNodeAt(int index) {
        return (CheckNode) rootNode.getChildren().getNodes()[index];
    }
}
