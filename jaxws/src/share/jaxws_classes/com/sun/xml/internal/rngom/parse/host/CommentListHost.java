package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;

class CommentListHost extends Base implements CommentList {

    final CommentList lhs;
    final CommentList rhs;

    CommentListHost(CommentList lhs, CommentList rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void addComment(String value, Location _loc) throws BuildException {
        LocationHost loc = cast(_loc);
        if(lhs!=null)
            lhs.addComment(value,loc.lhs);
        if(rhs!=null)
            rhs.addComment(value,loc.rhs);
    }
}
