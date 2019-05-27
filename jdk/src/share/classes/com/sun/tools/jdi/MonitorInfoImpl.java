package com.sun.tools.jdi;

import com.sun.jdi.*;

public class MonitorInfoImpl extends MirrorImpl
    implements MonitorInfo, ThreadListener {

    /* Once false, monitorInfo should not be used.
     * access synchronized on (vm.state())
     */
    private boolean isValid = true;

    ObjectReference monitor;
    ThreadReference thread;
    int  stack_depth;

    MonitorInfoImpl(VirtualMachine vm, ObjectReference mon,
                    ThreadReferenceImpl thread, int dpth) {
        super(vm);
        this.monitor = mon;
        this.thread = thread;
        this.stack_depth = dpth;
        thread.addListener(this);
    }


    /*
     * ThreadListener implementation
     * Must be synchronized since we must protect against
     * sending defunct (isValid == false) stack ids to the back-end.
     */
    public boolean threadResumable(ThreadAction action) {
        synchronized (vm.state()) {
            if (isValid) {
                isValid = false;
                return false;   /* remove this stack frame as a listener */
            } else {
                throw new InternalException(
                                  "Invalid stack frame thread listener");
            }
        }
    }

    private void validateMonitorInfo() {
        if (!isValid) {
            throw new InvalidStackFrameException("Thread has been resumed");
        }
    }

    public ObjectReference monitor() {
        validateMonitorInfo();
        return monitor;
    }

    public int stackDepth() {
        validateMonitorInfo();
        return stack_depth;
    }

    public ThreadReference thread() {
        validateMonitorInfo();
        return thread;
    }
}
