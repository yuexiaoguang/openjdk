package sun.invoke.util;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility routines for dealing with bytecode-level signatures.
 */
public class BytecodeDescriptor {

    private BytecodeDescriptor() { }  // cannot instantiate

    public static List<Class<?>> parseMethod(String bytecodeSignature, ClassLoader loader) {
        return parseMethod(bytecodeSignature, 0, bytecodeSignature.length(), loader);
    }

    static List<Class<?>> parseMethod(String bytecodeSignature,
            int start, int end, ClassLoader loader) {
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        String str = bytecodeSignature;
        int[] i = {start};
        ArrayList<Class<?>> ptypes = new ArrayList<Class<?>>();
        if (i[0] < end && str.charAt(i[0]) == '(') {
            ++i[0];  // skip '('
            while (i[0] < end && str.charAt(i[0]) != ')') {
                Class<?> pt = parseSig(str, i, end, loader);
                if (pt == null || pt == void.class)
                    parseError(str, "bad argument type");
                ptypes.add(pt);
            }
            ++i[0];  // skip ')'
        } else {
            parseError(str, "not a method type");
        }
        Class<?> rtype = parseSig(str, i, end, loader);
        if (rtype == null || i[0] != end)
            parseError(str, "bad return type");
        ptypes.add(rtype);
        return ptypes;
    }

    static private void parseError(String str, String msg) {
        throw new IllegalArgumentException("bad signature: "+str+": "+msg);
    }

    static private Class<?> parseSig(String str, int[] i, int end, ClassLoader loader) {
        if (i[0] == end)  return null;
        char c = str.charAt(i[0]++);
        if (c == 'L') {
            int begc = i[0], endc = str.indexOf(';', begc);
            if (endc < 0)  return null;
            i[0] = endc+1;
            String name = str.substring(begc, endc).replace('/', '.');
            try {
                return loader.loadClass(name);
            } catch (ClassNotFoundException ex) {
                throw new TypeNotPresentException(name, ex);
            }
        } else if (c == '[') {
            Class<?> t = parseSig(str, i, end, loader);
            if (t != null)
                t = java.lang.reflect.Array.newInstance(t, 0).getClass();
            return t;
        } else {
            return Wrapper.forBasicType(c).primitiveType();
        }
    }

    public static String unparse(Class<?> type) {
        StringBuilder sb = new StringBuilder();
        unparseSig(type, sb);
        return sb.toString();
    }

    public static String unparse(MethodType type) {
        return unparseMethod(type.returnType(), type.parameterList());
    }

    public static String unparse(Object type) {
        if (type instanceof Class<?>)
            return unparse((Class<?>) type);
        if (type instanceof MethodType)
            return unparse((MethodType) type);
        return (String) type;
    }

    public static String unparseMethod(Class<?> rtype, List<Class<?>> ptypes) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> pt : ptypes)
            unparseSig(pt, sb);
        sb.append(')');
        unparseSig(rtype, sb);
        return sb.toString();
    }

    static private void unparseSig(Class<?> t, StringBuilder sb) {
        char c = Wrapper.forBasicType(t).basicTypeChar();
        if (c != 'L') {
            sb.append(c);
        } else {
            boolean lsemi = (!t.isArray());
            if (lsemi)  sb.append('L');
            sb.append(t.getName().replace('.', '/'));
            if (lsemi)  sb.append(';');
        }
    }

}
