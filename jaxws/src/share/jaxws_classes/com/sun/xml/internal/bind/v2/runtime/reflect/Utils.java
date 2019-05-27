package com.sun.xml.internal.bind.v2.runtime.reflect;

import com.sun.xml.internal.bind.v2.model.nav.Navigator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utils class.
 * Has *package private* access to avoid inappropriate usage.
 */
/* package */ final class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    /**
     * static ReflectionNavigator field to avoid usage of reflection every time we use it.
     */
    /* package */ static final Navigator<Type, Class, Field, Method> REFLECTION_NAVIGATOR;

    static { // we statically initializing REFLECTION_NAVIGATOR property
        Class refNav = null;
        try {
            refNav = Class.forName("com.sun.xml.internal.bind.v2.model.nav.ReflectionNavigator");
            //noinspection unchecked
            Method getInstance = refNav.getDeclaredMethod("getInstance");
            getInstance.setAccessible(true);
            //noinspection unchecked
            REFLECTION_NAVIGATOR = (Navigator<Type, Class, Field, Method>) getInstance.invoke(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't find ReflectionNavigator class");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException("ReflectionNavigator.getInstance throws the exception");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalStateException("ReflectionNavigator.getInstance can't be found");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException("ReflectionNavigator.getInstance method is inaccessible");
        } catch (SecurityException e) {
            LOGGER.log(Level.FINE, "Unable to access ReflectionNavigator.getInstance", e);
            throw e;
        }
    }

    /**
     * private constructor to avoid util class instantiating
     */
    private Utils() {
    }
}
