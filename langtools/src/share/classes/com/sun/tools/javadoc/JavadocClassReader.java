package com.sun.tools.javadoc;

import java.util.EnumSet;
import javax.tools.JavaFileObject;

import com.sun.tools.javac.code.Symbol.PackageSymbol;
import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.util.Context;

/** Javadoc uses an extended class reader that records package.html entries
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class JavadocClassReader extends ClassReader {

    public static JavadocClassReader instance0(Context context) {
        ClassReader instance = context.get(classReaderKey);
        if (instance == null)
            instance = new JavadocClassReader(context);
        return (JavadocClassReader)instance;
    }

    public static void preRegister(Context context) {
        context.put(classReaderKey, new Context.Factory<ClassReader>() {
            public ClassReader make(Context c) {
                return new JavadocClassReader(c);
            }
        });
    }

    private DocEnv docenv;
    private EnumSet<JavaFileObject.Kind> all = EnumSet.of(JavaFileObject.Kind.CLASS,
                                                          JavaFileObject.Kind.SOURCE,
                                                          JavaFileObject.Kind.HTML);
    private EnumSet<JavaFileObject.Kind> noSource = EnumSet.of(JavaFileObject.Kind.CLASS,
                                                               JavaFileObject.Kind.HTML);

    public JavadocClassReader(Context context) {
        super(context, true);
        docenv = DocEnv.instance(context);
        preferSource = true;
    }

    /**
     * Override getPackageFileKinds to include search for package.html
     */
    @Override
    protected EnumSet<JavaFileObject.Kind> getPackageFileKinds() {
        return docenv.docClasses ? noSource : all;
    }

    /**
     * Override extraFileActions to check for package documentation
     */
    @Override
    protected void extraFileActions(PackageSymbol pack, JavaFileObject fo) {
        if (fo.isNameCompatible("package", JavaFileObject.Kind.HTML))
            docenv.getPackageDoc(pack).setDocPath(fo);
    }
}
