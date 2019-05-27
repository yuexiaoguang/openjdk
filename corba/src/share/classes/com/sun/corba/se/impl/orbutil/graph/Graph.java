package com.sun.corba.se.impl.orbutil.graph ;

import java.util.Set ;

public interface Graph extends Set // Set<Node>
{
    NodeData getNodeData( Node node ) ;

    Set /* Set<Node> */ getRoots() ;
}
