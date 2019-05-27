package com.sun.tools.hat.internal.server;

import com.sun.tools.hat.internal.oql.*;

/**
 * This handles Object Query Language (OQL) queries.
 */
class OQLQuery extends QueryHandler {

    public OQLQuery(OQLEngine engine) {
        this.engine = engine;
    }

    public void run() {
        startHtml("Object Query Language (OQL) query");
        String oql = null;
        if (query != null && !query.equals("")) {
            int index = query.indexOf("?query=");
            if (index != -1 && query.length() > 7) {
                oql = query.substring(index + 7);
            }
        }
        out.println("<p align='center'><table>");
        out.println("<tr><td><b>");
        out.println("<a href='/'>All Classes (excluding platform)</a>");
        out.println("</b></td>");
        out.println("<td><b><a href='/oqlhelp/'>OQL Help</a></b></td></tr>");
        out.println("</table></p>");
        out.println("<form action='/oql/' method='get'>");
        out.println("<p align='center'>");
        out.println("<textarea name='query' cols=80 rows=10>");
        if (oql != null) {
            println(oql);
        }
        out.println("</textarea>");
        out.println("</p>");
        out.println("<p align='center'>");
        out.println("<input type='submit' value='Execute'></input>");
        out.println("</p>");
        out.println("</form>");
        if (oql != null) {
            executeQuery(oql);
        }
        endHtml();
    }

    private void executeQuery(String q) {
        try {
            out.println("<table border='1'>");
            engine.executeQuery(q, new ObjectVisitor() {
                     public boolean visit(Object o) {
                         out.println("<tr><td>");
                         try {
                             out.println(engine.toHtml(o));
                         } catch (Exception e) {
                             printException(e);
                         }
                         out.println("</td></tr>");
                         return false;
                     }
                 });
            out.println("</table>");
        } catch (OQLException exp) {
            printException(exp);
        }
    }

    private OQLEngine engine;
}
