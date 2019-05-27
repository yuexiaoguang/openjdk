package com.sun.source.util;


/**
 * Provides a listener to monitor the activity of the JDK Java Compiler, javac.
 */
@jdk.Exported
public interface TaskListener
{
    public void started(TaskEvent e);

    public void finished(TaskEvent e);
}
