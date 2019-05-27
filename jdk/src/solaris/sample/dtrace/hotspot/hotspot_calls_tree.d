#!/usr/sbin/dtrace -Zs
/*
 * Usage:
 *   1. hotspot_calls_tree.d -c "java ..."
 *   2. hotspot_calls_tree.d -p JAVA_PID
 *
 * This script prints calls tree of fired 'hotspot' probes.
 *
 * Notes: 
 *    The script uses 'monitors' probes which are disabled by default since
 *    it incurs performance overhead to the application. To enable them, you
 *    need to turn on the ExtendedDTraceProbes VM option. You can either
 *    start the application with -XX:+ExtendedDTraceProbes option or use the
 *    jinfo command to enable it at runtime as follows:
 *
 *       jinfo -flag +ExtendedDTraceProbes <java_pid>
 *
 */

#pragma D option quiet
#pragma D option destructive
#pragma D option defaultargs
#pragma D option aggrate=100ms

self int indent;
string PAUSE_AT_STARTUP_FILE;

:::BEGIN
{
    SAMPLE_NAME = "hotspot probes tracing";

    printf("BEGIN %s\n\n", SAMPLE_NAME);

    self->indent = 10;
}

hotspot$target:::class-loaded,
hotspot$target:::class-unloaded,
hotspot$target:::compiled-method-load,
hotspot$target:::compiled-method-unload,
hotspot$target:::monitor-notify,
hotspot$target:::monitor-notifyAll
{
    printf("%d %*s <-> %s\n", curcpu->cpu_id, self->indent, "", probename);
}

hotspot$target:::vm-init-begin,
hotspot$target:::gc-begin,
hotspot$target:::mem-pool-gc-begin,
hotspot$target:::thread-start,
hotspot$target:::method-compile-begin,
hotspot$target:::monitor-contended-enter,
hotspot$target:::monitor-wait
{
    self->indent ++;
    printf("%d %*s -> %s\n", curcpu->cpu_id, self->indent, "", probename);
}

hotspot$target:::vm-init-end,
hotspot$target:::vm-shutdown,
hotspot$target:::gc-end,
hotspot$target:::mem-pool-gc-end,
hotspot$target:::thread-stop,
hotspot$target:::method-compile-end,
hotspot$target:::monitor-contended-entered,
hotspot$target:::monitor-contended-exit,
hotspot$target:::monitor-waited
{
    printf("%d %*s <- %s\n", curcpu->cpu_id, self->indent, "", probename);
    self->indent --;
}

:::END
{
    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscall::rexit:entry,
syscall::exit:entry
/pid == $target/
{
    exit(0);
}
