package com.sun.tools.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class JavacompilerMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.tools.internal.ws.resources.javacompiler");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableJAVACOMPILER_CLASSPATH_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.classpath.error", arg0);
    }

    /**
     * {0} is not available in the classpath, requires Sun's JDK version 5.0 or latter.
     *
     */
    public static String JAVACOMPILER_CLASSPATH_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_CLASSPATH_ERROR(arg0));
    }

    public static Localizable localizableJAVACOMPILER_NOSUCHMETHOD_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.nosuchmethod.error", arg0);
    }

    /**
     * There is no such method {0} available, requires Sun's JDK version 5.0 or latter.
     *
     */
    public static String JAVACOMPILER_NOSUCHMETHOD_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_NOSUCHMETHOD_ERROR(arg0));
    }

    public static Localizable localizableJAVACOMPILER_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.error", arg0);
    }

    /**
     * error : {0}.
     *
     */
    public static String JAVACOMPILER_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_ERROR(arg0));
    }

}
