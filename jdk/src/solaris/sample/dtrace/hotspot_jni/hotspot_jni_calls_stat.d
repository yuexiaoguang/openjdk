#!/usr/sbin/dtrace -Zs

/*
 * Usage:
 *    1. hotspot_jni_calls_stat.d -c "java ..."
 *    2. hotspot_jni_calls_stat.d -p JAVA_PID
 *
 * This script collects statistics about how many times particular JNI
 * method has been called.
 *
 */


#pragma D option quiet
#pragma D option destructive
#pragma D option defaultargs
#pragma D option bufsize=16m
#pragma D option aggrate=100ms


:::BEGIN
{
    printf("BEGIN hotspot_jni tracing\n");
}


hotspot_jni$target:::*-entry
{
    JNI_CALLS ++;
    @jni_calls[probename] = count();
}

:::END
{
    printa("%10@d %s\n", @jni_calls);
    printf("\n");
    printf("Total number of JNI calls: %d\n", JNI_CALLS);

    printf("\nEND hotspot_jni tracing.\n");
}

syscall::rexit:entry,
syscall::exit:entry
/pid == $target/
{
   exit(0);
}
