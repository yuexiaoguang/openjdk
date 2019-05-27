#!/usr/sbin/dtrace -Zs

/*
 * Usage:
 *    1. object_allocation_stat.d -c "java ..." TOP_RESULTS_COUNT INTERVAL_SECS
 *    2. object_allocation_stat.d -p JAVA_PID TOP_RESULTS_COUNT INTERVAL_SECS
 *
 * This script collects statistics about TOP_RESULTS_COUNT (default is 25)
 * object allocations every INTERVAL_SECS (default is 60) seconds.
 *
 * The results are displayed in ascending order which means that the highest
 * allocated type is listed last. The script can be improved to sort the
 * results in reverse order when DTrace supports it.
 *
 * Notes:
 *  - The object-alloc probe is disabled by default since it incurs
 *    performance overhead to the application. To trace object-alloc probe,
 *    you need to turn on the ExtendedDTraceProbes VM option.
 *    You can either start the application with -XX:+ExtendedDTraceProbes
 *    option or use the jinfo command to enable it at runtime as follows:
 *
 *       jinfo -flag +ExtendedDTraceProbes <java_pid>
 *
 */

#pragma D option quiet
#pragma D option destructive
#pragma D option defaultargs
#pragma D option bufsize=16m
#pragma D option aggrate=100ms


self char *str_ptr;
self string class_name;

long long ALLOCATED_OBJECTS_CNT;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "hotspot object allocation tracing";

    TOP_RESULTS_COUNT = $1 ? $1 : 25;
    INTERVAL_SECS = $2 ? $2 : 60;

    ALLOCATED_OBJECTS_CNT = 0;

    SAMPLING_TIME = timestamp + INTERVAL_SECS * 1000000000ull;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::object-alloc probe arguments:
 *  arg0: uintptr_t,    Java thread id
 *  arg1: char*,        a pointer to mUTF-8 string containing the name of
 *                          the class of the object being allocated
 *  arg2: uintptr_t,    the length of the class name (in bytes)
 *  arg3: uintptr_t,    the size of the object being allocated
 */
hotspot$target:::object-alloc
{
    ALLOCATED_OBJECTS_CNT ++;

    self->str_ptr = (char*) copyin(arg1, arg2+1);
    self->str_ptr[arg2] = '\0';
    self->class_name = (string) self->str_ptr;


    @allocs_count[self->class_name] = count();
    @allocs_size[self->class_name] = sum(arg3);
}

tick-1sec
/timestamp > SAMPLING_TIME/
{
    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", walltimestamp);
    printf("%s\n", LINE_SEP);

    printf("\n");
    printf("Top %d allocations by size:\n", TOP_RESULTS_COUNT);
    trunc(@allocs_size, TOP_RESULTS_COUNT);
    printa("%10@d %s\n", @allocs_size);

    printf("\n");
    printf("Top %d allocations by count:\n", TOP_RESULTS_COUNT);
    trunc(@allocs_count, TOP_RESULTS_COUNT);
    printa("%10@d %s\n", @allocs_count);

    printf("\nTotal number of allocated objects: %d\n", ALLOCATED_OBJECTS_CNT);

    SAMPLING_TIME = timestamp + INTERVAL_SECS * 1000000000ull;
}

:::END
{
    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", walltimestamp);
    printf("%s\n", LINE_SEP);

    printf("\n");
    printf("Top %d allocations by size:\n", TOP_RESULTS_COUNT);
    trunc(@allocs_size, TOP_RESULTS_COUNT);
    printa("%10@d %s\n", @allocs_size);

    printf("\n");
    printf("Top %d allocations by count:\n", TOP_RESULTS_COUNT);
    trunc(@allocs_count, TOP_RESULTS_COUNT);
    printa("%10@d %s\n", @allocs_count);

    printf("\nTotal number of allocated objects: %d\n", ALLOCATED_OBJECTS_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscall::rexit:entry,
syscall::exit:entry
/pid == $target/
{
   exit(0);
}
