#!/usr/sbin/dtrace -Zs

/*
 * Usage:
 *   1. hotspot_jni_calls_tree.d -c "java ..."
 *   2. hotspot_jni_calls_tree.d -p JAVA_PID
 *
 * The script prints tree of JNI method calls.
 *
 */

#pragma D option quiet
#pragma D option destructive
#pragma D option defaultargs
#pragma D option bufsize=16m
#pragma D option aggrate=100ms


self int indent;

:::BEGIN
{
    printf("BEGIN hotspot_jni tracing\n");
}


hotspot_jni$target:::*
/!self->indent/
{
    self->indent = 11;
}

hotspot_jni$target:::*-entry
{
    self->indent++;
    printf("%d %*s -> %s\n", curcpu->cpu_id, self->indent, "", probename);
}


hotspot_jni$target:::*-return
{
    printf("%d %*s <- %s\n", curcpu->cpu_id, self->indent, "", probename);
    self->indent--;
}

:::END
{
   printf("\nEND hotspot_jni tracing.\n");

}

syscall::rexit:entry,
syscall::exit:entry
/pid == $target/
{
   exit(0);
}
