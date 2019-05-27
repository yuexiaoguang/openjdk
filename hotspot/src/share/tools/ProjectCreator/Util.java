import java.util.*;
import java.io.File;

public class Util {

    static String join(String padder, Vector<String> v) {
        return join(padder, v, false);
    }

    static String join(String padder, Vector<String> v, boolean quoted) {
        StringBuffer sb = new StringBuffer();

        for (Iterator<String> iter = v.iterator(); iter.hasNext(); ) {
            if (quoted) {
                sb.append('"');
            }
            sb.append(iter.next());
            if (quoted) {
                sb.append('"');
            }
            if (iter.hasNext()) sb.append(padder);
        }

        return sb.toString();
    }


    static String prefixed_join(String padder, Vector<String> v, boolean quoted) {
        StringBuffer sb = new StringBuffer();

        for (Iterator<String> iter = v.iterator(); iter.hasNext(); ) {
            sb.append(padder);

            if (quoted) {
                sb.append('"');
            }
            sb.append((String)iter.next());
            if (quoted) {
                sb.append('"');
            }
        }

        return sb.toString();
    }


    static String normalize(String file) {
        file = file.replace('\\', '/');
        if (file.length() > 2) {
            if (file.charAt(1) == ':' && file.charAt(2) == '/') {
                // convert drive letter to uppercase
                String drive = file.substring(0, 1).toUpperCase();
                return drive + file.substring(1);
            }
        }
        return file;
    }

    static String sep = File.separator;
}
