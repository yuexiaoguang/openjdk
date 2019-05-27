package jdk.nashorn.internal.runtime.regexp.joni;

public class Option {

    /* options */
    public static final int NONE                 = 0;
    public static final int IGNORECASE           = (1<<0);
    public static final int EXTEND               = (1<<1);
    public static final int MULTILINE            = (1<<2);
    public static final int SINGLELINE           = (1<<3);
    public static final int FIND_LONGEST         = (1<<4);
    public static final int FIND_NOT_EMPTY       = (1<<5);
    public static final int NEGATE_SINGLELINE    = (1<<6);
    public static final int DONT_CAPTURE_GROUP   = (1<<7);
    public static final int CAPTURE_GROUP        = (1<<8);

    /* options (search time) */
    public static final int NOTBOL               = (1<<9);
    public static final int NOTEOL               = (1<<10);
    public static final int POSIX_REGION         = (1<<11);
    public static final int MAXBIT               = (1<<12); /* limit */

    public static final int DEFAULT              = NONE;

    public static String toString(int option) {
        String options = "";
        if (isIgnoreCase(option)) options += "IGNORECASE ";
        if (isExtend(option)) options += "EXTEND ";
        if (isMultiline(option)) options += "MULTILINE ";
        if (isSingleline(option)) options += "SINGLELINE ";
        if (isFindLongest(option)) options += "FIND_LONGEST ";
        if (isFindNotEmpty(option)) options += "FIND_NOT_EMPTY  ";
        if (isNegateSingleline(option)) options += "NEGATE_SINGLELINE ";
        if (isDontCaptureGroup(option)) options += "DONT_CAPTURE_GROUP ";
        if (isCaptureGroup(option)) options += "CAPTURE_GROUP ";

        if (isNotBol(option)) options += "NOTBOL ";
        if (isNotEol(option)) options += "NOTEOL ";
        if (isPosixRegion(option)) options += "POSIX_REGION ";

        return options;
    }

    public static boolean isIgnoreCase(int option) {
        return (option & IGNORECASE) != 0;
    }

    public static boolean isExtend(int option) {
        return (option & EXTEND) != 0;
    }

    public static boolean isSingleline(int option) {
        return (option & SINGLELINE) != 0;
    }

    public static boolean isMultiline(int option) {
        return (option & MULTILINE) != 0;
    }

    public static boolean isFindLongest(int option) {
        return (option & FIND_LONGEST) != 0;
    }

    public static boolean isFindNotEmpty(int option) {
        return (option & FIND_NOT_EMPTY) != 0;
    }

    public static boolean isFindCondition(int option) {
        return (option & (FIND_LONGEST | FIND_NOT_EMPTY)) != 0;
    }

    public static boolean isNegateSingleline(int option) {
        return (option & NEGATE_SINGLELINE) != 0;
    }

    public static boolean isDontCaptureGroup(int option) {
        return (option & DONT_CAPTURE_GROUP) != 0;
    }

    public static boolean isCaptureGroup(int option) {
        return (option & CAPTURE_GROUP) != 0;
    }

    public static boolean isNotBol(int option) {
        return (option & NOTBOL) != 0;
    }

    public static boolean isNotEol(int option) {
        return (option & NOTEOL) != 0;
    }

    public static boolean isPosixRegion(int option) {
        return (option & POSIX_REGION) != 0;
    }

    /* OP_SET_OPTION is required for these options.  ??? */
    //    public static boolean isDynamic(int option) {
    //        return (option & (MULTILINE | IGNORECASE)) != 0;
    //    }
    public static boolean isDynamic(int option) {
        return false;
    }
}
