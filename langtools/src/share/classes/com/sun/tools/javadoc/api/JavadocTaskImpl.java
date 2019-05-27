package com.sun.tools.javadoc.api;

import com.sun.tools.javac.util.ClientCodeException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.tools.DocumentationTool.DocumentationTask;
import javax.tools.JavaFileObject;

import com.sun.tools.javac.util.Context;
import com.sun.tools.javadoc.Start;
import java.util.Collections;

/**
 * Provides access to functionality specific to the JDK documentation tool,
 * javadoc.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public class JavadocTaskImpl implements DocumentationTask {
    private final AtomicBoolean used = new AtomicBoolean();

    private final Context context;
    private Class<?> docletClass;
    private Iterable<String> options;
    private Iterable<? extends JavaFileObject> fileObjects;
    private Locale locale;

    public JavadocTaskImpl(Context context, Class<?> docletClass,
            Iterable<String> options, Iterable<? extends JavaFileObject> fileObjects) {
        this.context = context;
        this.docletClass = docletClass;

        this.options = (options == null) ? Collections.<String>emptySet()
                : nullCheck(options);
        this.fileObjects = (fileObjects == null) ? Collections.<JavaFileObject>emptySet()
                : nullCheck(fileObjects);
        setLocale(Locale.getDefault());
    }

    public void setLocale(Locale locale) {
        if (used.get())
            throw new IllegalStateException();
        this.locale = locale;
    }

    public Boolean call() {
        if (!used.getAndSet(true)) {
            initContext();
            Start jdoc = new Start(context);
            try {
                return jdoc.begin(docletClass, options, fileObjects);
            } catch (ClientCodeException e) {
                throw new RuntimeException(e.getCause());
            }
        } else {
            throw new IllegalStateException("multiple calls to method 'call'");
        }
    }

    private void initContext() {
        //initialize compiler's default locale
        context.put(Locale.class, locale);
    }

    private static <T> Iterable<T> nullCheck(Iterable<T> items) {
        for (T item: items) {
            if (item == null)
                throw new NullPointerException();
        }
        return items;
    }
}
