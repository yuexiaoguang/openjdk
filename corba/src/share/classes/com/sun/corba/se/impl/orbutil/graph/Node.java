package com.sun.corba.se.impl.orbutil.graph ;

import java.util.Set ;

/** Node in a graph.
*/
public interface Node
{
    /** Get all the children of this node.
     */
    Set /* Set<Node> */ getChildren() ;
}
