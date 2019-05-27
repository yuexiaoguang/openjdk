package com.sun.tools.internal.xjc.addon.locator;

import java.io.IOException;

import javax.xml.bind.annotation.XmlTransient;

import com.sun.codemodel.internal.JDefinedClass;
import com.sun.codemodel.internal.JMod;
import com.sun.codemodel.internal.JVar;
import com.sun.codemodel.internal.JMethod;
import com.sun.tools.internal.xjc.BadCommandLineException;
import com.sun.tools.internal.xjc.Options;
import com.sun.tools.internal.xjc.Plugin;
import com.sun.tools.internal.xjc.outline.ClassOutline;
import com.sun.tools.internal.xjc.outline.Outline;
import com.sun.xml.internal.bind.Locatable;
import com.sun.xml.internal.bind.annotation.XmlLocation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;

/**
 * Generates JAXB objects that implement {@link Locatable}.
 */
public class SourceLocationAddOn extends Plugin {

    public String getOptionName() {
        return "Xlocator";
    }

    public String getUsage() {
        return "  -Xlocator          :  enable source location support for generated code";
    }

    public int parseArgument(Options opt, String[] args, int i) throws BadCommandLineException, IOException {
        return 0;   // no option recognized
    }

    private static final String fieldName = "locator";

    public boolean run(
        Outline outline,
        Options opt,
        ErrorHandler errorHandler ) {

        for( ClassOutline ci : outline.getClasses() ) {
            JDefinedClass impl = ci.implClass;
            if (ci.getSuperClass() == null) {
                JVar $loc = impl.field(JMod.PROTECTED, Locator.class, fieldName);
                $loc.annotate(XmlLocation.class);
                $loc.annotate(XmlTransient.class);

                impl._implements(Locatable.class);

                impl.method(JMod.PUBLIC, Locator.class, "sourceLocation").body()._return($loc);

                JMethod setter = impl.method(JMod.PUBLIC, Void.TYPE, "setSourceLocation");
                JVar $newLoc = setter.param(Locator.class, "newLocator");
                setter.body().assign($loc, $newLoc);
            }
        }

        return true;
    }
}
