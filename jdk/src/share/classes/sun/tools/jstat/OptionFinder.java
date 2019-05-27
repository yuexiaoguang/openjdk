package sun.tools.jstat;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * A class for finding a specific special option in the jstat_options file.
 */
public class OptionFinder {

    private static final boolean debug = false;

    List<URL> optionsSources;

    public OptionFinder(List<URL> optionsSources) {
        this.optionsSources = optionsSources;
    }

    public OptionFormat getOptionFormat(String option, boolean useTimestamp) {
        OptionFormat of = getOptionFormat(option, optionsSources);
        OptionFormat tof = null;
        if ((of != null) && (useTimestamp)) {
            // prepend the timestamp column as first column
            tof = getOptionFormat("timestamp", optionsSources);
            if (tof != null) {
                ColumnFormat cf = (ColumnFormat)tof.getSubFormat(0);
                of.insertSubFormat(0, cf);
            }
        }
        return of;
    }

    protected OptionFormat getOptionFormat(String option, List<URL> sources) {
        OptionFormat of = null;
        for (URL u : sources) {
            try {
                Reader r = new BufferedReader(
                        new InputStreamReader(u.openStream()));
                of = new Parser(r).parse(option);
                if (of != null)
                    break;
            } catch (IOException e) {
                if (debug) {
                    System.err.println("Error processing " + u
                                       + " : " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (ParserException e) {
                // Exception in parsing the options file.
                System.err.println(u + ": " + e.getMessage());
                System.err.println("Parsing of " + u + " aborted");
            }
        }
        return of;
    }
}
