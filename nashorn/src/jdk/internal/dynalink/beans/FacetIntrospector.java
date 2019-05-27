package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import jdk.internal.dynalink.support.Lookup;

/**
 * Base for classes that expose class field and method information to an {@link AbstractJavaLinker}. There are
 * subclasses for instance (bean) and static facet of a class.
 */
abstract class FacetIntrospector {
    private final Class<?> clazz;
    private final boolean instance;
    private final boolean isRestricted;

    protected final AccessibleMembersLookup membersLookup;

    FacetIntrospector(Class<?> clazz, boolean instance) {
        this.clazz = clazz;
        this.instance = instance;
        isRestricted = CheckRestrictedPackage.isRestrictedClass(clazz);
        membersLookup = new AccessibleMembersLookup(clazz, instance);
    }

    /**
     * Returns getters for inner classes.
     * @return getters for inner classes.
     */
    abstract Map<String, MethodHandle> getInnerClassGetters();

    /**
     * Returns the fields for the class facet.
     * @return the fields for the class facet.
     */
    Collection<Field> getFields() {
        if(isRestricted) {
            // NOTE: we can't do anything here. Unlike with methods in AccessibleMethodsLookup, we can't just return
            // the fields from a public superclass, because this class might define same-named fields which will shadow
            // the superclass fields, and we have no way to know if they do, since we're denied invocation of
            // getFields(). Therefore, the only correct course of action is to not expose any public fields from a class
            // defined in a restricted package.
            return Collections.emptySet();
        }

        final Field[] fields = clazz.getFields();
        final Collection<Field> cfields = new ArrayList<>(fields.length);
        for(Field field: fields) {
            if(instance != Modifier.isStatic(field.getModifiers()) && isAccessible(field)) {
                cfields.add(field);
            }
        }
        return cfields;
    }

    boolean isAccessible(Member m) {
        final Class<?> declaring = m.getDeclaringClass();
        // (declaring == clazz) is just an optimization - we're calling this only from code that operates on a
        // non-restriced class, so if the declaring class is identical to the class being inspected, then forego
        // a potentially expensive restricted-package check.
        return declaring == clazz || !CheckRestrictedPackage.isRestrictedClass(declaring);
    }

    /**
     * Returns all the methods in the facet.
     * @return all the methods in the facet.
     */
    Collection<Method> getMethods() {
        return membersLookup.getMethods();
    }


    MethodHandle unreflectGetter(Field field) {
        return editMethodHandle(Lookup.PUBLIC.unreflectGetter(field));
    }

    MethodHandle unreflectSetter(Field field) {
        return editMethodHandle(Lookup.PUBLIC.unreflectSetter(field));
    }

    /**
     * Returns an edited method handle. A facet might need to edit an unreflected method handle before it is usable with
     * the facet. By default, returns the passed method handle unchanged. The class' static facet will introduce a
     * dropArguments.
     * @param mh the method handle to edit.
     * @return the edited method handle.
     */
    abstract MethodHandle editMethodHandle(MethodHandle mh);
}
