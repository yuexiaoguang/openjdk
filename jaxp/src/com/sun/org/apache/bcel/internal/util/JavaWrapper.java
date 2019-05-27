/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import java.lang.reflect.*;

/**
 * Java interpreter replacement, i.e., wrapper that uses its own ClassLoader
 * to modify/generate classes as they're requested. You can take this as a template
 * for your own applications.<br>
 * Call this wrapper with
 * <pre>java com.sun.org.apache.bcel.internal.util.JavaWrapper &lt;real.class.name&gt; [arguments]</pre>
 * <p>
 * To use your own class loader you can set the "bcel.classloader" system property
 * which defaults to "com.sun.org.apache.bcel.internal.util.ClassLoader", e.g., with
 * <pre>java com.sun.org.apache.bcel.internal.util.JavaWrapper -Dbcel.classloader=foo.MyLoader &lt;real.class.name&gt; [arguments]</pre>
 * </p>
 */
public class JavaWrapper {
  private java.lang.ClassLoader loader;

  private static java.lang.ClassLoader getClassLoader() {
    String s = SecuritySupport.getSystemProperty("bcel.classloader");

    if((s == null) || "".equals(s))
      s = "com.sun.org.apache.bcel.internal.util.ClassLoader";

    try {
      return (java.lang.ClassLoader)Class.forName(s).newInstance();
    } catch(Exception e) {
      throw new RuntimeException(e.toString());
    }
  }

  public JavaWrapper(java.lang.ClassLoader loader) {
    this.loader = loader;
  }

  public JavaWrapper() {
    this(getClassLoader());
  }

  /** Runs the _main method of the given class with the arguments passed in argv
   *
   * @param class_name the fully qualified class name
   * @param argv the arguments just as you would pass them directly
   */
  public void runMain(String class_name, String[] argv) throws ClassNotFoundException
  {
    Class   cl    = loader.loadClass(class_name);
    Method method = null;

    try {
      method = cl.getMethod("_main",  new Class[] { argv.getClass() });

      /* Method _main is sane ?
       */
      int   m = method.getModifiers();
      Class r = method.getReturnType();

      if(!(Modifier.isPublic(m) && Modifier.isStatic(m)) ||
         Modifier.isAbstract(m) || (r != Void.TYPE))
        throw new NoSuchMethodException();
    } catch(NoSuchMethodException no) {
      System.out.println("In class " + class_name +
                         ": public static void _main(String[] argv) is not defined");
      return;
    }

    try {
      method.invoke(null, new Object[] { argv });
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Default _main method used as wrapper, expects the fully qualified class name
   * of the real class as the first argument.
   */
  public static void _main(String[] argv) throws Exception {
    /* Expects class name as first argument, other arguments are by-passed.
     */
    if(argv.length == 0) {
      System.out.println("Missing class name.");
      return;
    }

    String class_name = argv[0];
    String[] new_argv = new String[argv.length - 1];
    System.arraycopy(argv, 1, new_argv, 0, new_argv.length);

    JavaWrapper wrapper = new JavaWrapper();
    wrapper.runMain(class_name, new_argv);
  }
}
