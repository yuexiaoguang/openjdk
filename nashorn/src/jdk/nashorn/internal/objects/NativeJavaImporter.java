package jdk.nashorn.internal.objects;

import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Constructor;
import jdk.nashorn.internal.objects.annotations.Function;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.NativeJavaPackage;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;

/**
 * This is "JavaImporter" constructor. This constructor allows you to use Java types omitting explicit package names.
 * Objects of this constructor are used along with {@code "with"} statements and as such are not usable in ECMAScript
 * strict mode. Example:
 * <pre>
 *     var imports = new JavaImporter(java.util, java.io);
 *     with (imports) {
 *         var m = new HashMap(); // java.util.HashMap
 *         var f = new File("."); // java.io.File
 *         ...
 *     }
 * </pre>
 * Note however that the preferred way for accessing Java types in Nashorn is through the use of
 * {@link NativeJava#type(Object, Object) Java.type()} method.
 */
@ScriptClass("JavaImporter")
public final class NativeJavaImporter extends ScriptObject {
    private final Object[] args;

    // initialized by nasgen
    private static PropertyMap $nasgenmap$;

    static PropertyMap getInitialMap() {
        return $nasgenmap$;
    }

    private NativeJavaImporter(final Object[] args, final ScriptObject proto, final PropertyMap map) {
        super(proto, map);
        this.args = args;
    }

    private NativeJavaImporter(final Object[] args, final Global global) {
        this(args, global.getJavaImporterPrototype(), global.getJavaImporterMap());
    }

    private NativeJavaImporter(final Object[] args) {
        this(args, Global.instance());
    }

    @Override
    public String getClassName() {
        return "JavaImporter";
    }

    /**
     * Constructor
     * @param isNew is the new operator used for instantiating this NativeJavaImporter
     * @param self self reference
     * @param args arguments
     * @return NativeJavaImporter instance
     */
    @Constructor(arity = 1)
    public static Object constructor(final boolean isNew, final Object self, final Object... args) {
        return new NativeJavaImporter(args);
    }

    /**
     * "No such property" call placeholder.
     *
     * This can never be called as we override {@link ScriptObject#noSuchProperty}. We do declare it here as it's a signal
     * to {@link jdk.nashorn.internal.runtime.WithObject} that it's worth trying doing a {@code noSuchProperty} on this object.
     *
     * @param self self reference
     * @param name property name
     * @return never returns
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE)
    public static Object __noSuchProperty__(final Object self, final Object name) {
        throw new AssertionError("__noSuchProperty__ placeholder called");
    }

    /**
     * "No such method call" placeholder
     *
     * This can never be called as we override {@link ScriptObject#noSuchMethod}. We do declare it here as it's a signal
     * to {@link jdk.nashorn.internal.runtime.WithObject} that it's worth trying doing a noSuchProperty on this object.
     *
     * @param self self reference
     * @param args arguments to method
     * @return never returns
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE)
    public static Object __noSuchMethod__(final Object self, final Object... args) {
        throw new AssertionError("__noSuchMethod__ placeholder called");
    }

    @Override
    public GuardedInvocation noSuchProperty(final CallSiteDescriptor desc, final LinkRequest request) {
        return createAndSetProperty(desc) ? super.lookup(desc, request) : super.noSuchProperty(desc, request);
    }

    @Override
    public GuardedInvocation noSuchMethod(final CallSiteDescriptor desc, final LinkRequest request) {
        return createAndSetProperty(desc) ? super.lookup(desc, request) : super.noSuchMethod(desc, request);
    }

    private boolean createAndSetProperty(final CallSiteDescriptor desc) {
        final String name = desc.getNameToken(CallSiteDescriptor.NAME_OPERAND);
        final Object value = createProperty(name);
        if(value != null) {
            set(name, value, false);
            return true;
        }
        return false;
    }

    private Object createProperty(final String name) {
        final int len = args.length;

        for (int i = len - 1; i > -1; i--) {
            final Object obj = args[i];

            if (obj instanceof StaticClass) {
                if (((StaticClass)obj).getRepresentedClass().getSimpleName().equals(name)) {
                    return obj;
                }
            } else if (obj instanceof NativeJavaPackage) {
                final String pkgName  = ((NativeJavaPackage)obj).getName();
                final String fullName = pkgName.isEmpty() ? name : (pkgName + "." + name);
                final Context context = Global.instance().getContext();
                try {
                    return StaticClass.forClass(context.findClass(fullName));
                } catch (final ClassNotFoundException e) {
                    // IGNORE
                }
            }
        }
        return null;
    }
}
