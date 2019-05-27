package com.sun.jmx.snmp.tasks;

/**
 * This interface is implemented by objects that are able to execute
 * tasks. Whether the task is executed in the client thread or in another
 * thread depends on the TaskServer implementation.
 *
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 **/
public interface TaskServer {
    /**
     * Submit a task to be executed.
     * Once a task is submitted, it is guaranteed that either
     * {@link com.sun.jmx.snmp.tasks.Task#run() task.run()} or
     * {@link com.sun.jmx.snmp.tasks.Task#cancel() task.cancel()} will be called.
     * <p>Whether the task is executed in the client thread (e.g.
     * <code>public void submitTask(Task task) { task.run(); }</code>) or in
     * another thread (e.g. <code>
     * public void submitTask(Task task) { new Thrad(task).start(); }</code>)
     * depends on the TaskServer implementation.
     * @param task The task to be executed.
     **/
    public void submitTask(Task task);
}
