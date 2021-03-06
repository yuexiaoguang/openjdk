#!/usr/sbin/dtrace -Zs
/*
 * Usage:
 *   1. method_compile_stat.d -c "java ..." TOP_RESULTS_COUNT INTERVAL_SECS
 *   2. method_compile_stat.d -p JAVA_PID TOP_RESULTS_COUNT INTERVAL_SECS
 *
 * This script prints statistics about TOP_RESULTS_COUNT (default is 25)
 * methods with largest/smallest compilation time every INTERVAL_SECS
 * (default is 60) seconds.
 *
 */

#pragma D option quiet
#pragma D option destructive
#pragma D option defaultargs
#pragma D option aggrate=100ms


self char *str_ptr;
self string class_name;
self string method_name;
self string signature;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "hotspot methods compilation tracing";

    TOP_RESULTS_COUNT = $1 ? $1 : 25;
    INTERVAL_SECS = $2 ? $2 : 60;

    COMPILED_METHODS_COUNT = 0;
    LOADED_METHODS_CNT = 0;
    UNLOADED_METHODS_CNT = 0;

    SAMPLING_TIME = timestamp + INTERVAL_SECS * 1000000000ull;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::method-compile-begin
 *  arg0: char*,        a pointer to mUTF-8 string containing the name of
 *                          the compiler
 *  arg1: uintptr_t,    the length of the compiler name (in bytes)
 *  arg2: char*,        a pointer to mUTF-8 string containing the class name of
 *                          the method being compiled
 *  arg3: uintptr_t,    the length of the class name (in bytes)
 *  arg4: char*,        a pointer to mUTF-8 string containing the method name of
 *                          the method being compiled
 *  arg5: uintptr_t,    the length of the method name (in bytes)
 *  arg6: char*,        a pointer to mUTF-8 string containing the signature of
 *                          the method being compiled
 *  arg7: uintptr_t,    the length of the signature(in bytes)
 */
hotspot$target:::method-compile-begin
{
    /*compiler_name, len, class_name, len, method_name, len, signature, len*/

    self->str_ptr = (char*) copyin(arg0, arg1+1);
    self->str_ptr[arg1] = '\0';
    compiler_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg2, arg3+1);
    self->str_ptr[arg3] = '\0';
    self->class_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg4, arg5+1);
    self->str_ptr[arg5] = '\0';
    self->method_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg6, arg7+1);
    self->str_ptr[arg7] = '\0';
    self->signature = (string) self->str_ptr;

    self->ts[self->class_name, self->method_name, self->signature] = timestamp;
}

/*
 * hotspot:::method-compile-end
 *  arg0: char*,        a pointer to mUTF-8 string containing the name of
 *                          the compiler
 *  arg1: uintptr_t,    the length of the compiler name (in bytes)
 *  arg2: char*,        a pointer to mUTF-8 string containing the class name of
 *                          the method being compiled
 *  arg3: uintptr_t,    the length of the class name (in bytes)
 *  arg4: char*,        a pointer to mUTF-8 string containing the method name of
 *                          the method being compiled
 *  arg5: uintptr_t,    the length of the method name (in bytes)
 *  arg6: char*,        a pointer to mUTF-8 string containing the signature of
 *                          the method being compiled
 *  arg7: uintptr_t,    the length of the signature(in bytes)
 *  arg8: uintptr_t,    boolean value which indicates if method
 *                          has been compiled successfuly
 */
hotspot$target:::method-compile-end
{
    /* compiler_name, len, class_name, len, method_name, len,
       signature, len, isSuccess */

    self->str_ptr = (char*) copyin(arg0, arg1+1);
    self->str_ptr[arg1] = '\0';
    compiler_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg2, arg3+1);
    self->str_ptr[arg3] = '\0';
    self->class_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg4, arg5+1);
    self->str_ptr[arg5] = '\0';
    self->method_name = (string) self->str_ptr;

    self->str_ptr = (char*) copyin(arg6, arg7+1);
    self->str_ptr[arg7] = '\0';
    self->signature = (string) self->str_ptr;
}

/*
 * Method was successfuly compiled
 */
hotspot$target:::method-compile-end
/arg8 && self->ts[self->class_name, self->method_name, self->signature]/
{
    /* compiler_name, len, class_name, len, method_name, len,
       signature, len, isSuccess */

    COMPILED_METHODS_COUNT++;

    @compile_time_top[self->class_name, self->method_name, self->signature] =
     avg((timestamp -
      self->ts[self->class_name, self->method_name, self->signature]) / 1000);

    @compile_time_last[self->class_name, self->method_name, self->signature] =
     avg((timestamp -
      self->ts[self->class_name, self->method_name, self->signature]) / 1000);

    self->ts[self->class_name, self->method_name, self->signature] = 0;
}

/*
 * Method compilation was failed
 */
hotspot$target:::method-compile-end
/arg8 != 1 && self->ts[self->class_name, self->method_name, self->signature]/
{
    /* compiler_name, len, class_name, len, method_name, len,
       signature, len, isSuccess */

    @fail_compile_count[self->class_name,
                        self->method_name, self->signature] = count();
}

hotspot$target:::compiled-method-load
{
    /* class_name, len, method_name, len, signature, len, code_address, size */

    LOADED_METHODS_CNT ++;
}

hotspot$target:::compiled-method-unload
{
    /* class_name, len, method_name, len, signature, len, code_address, size */

    UNLOADED_METHODS_CNT ++;
}


tick-1sec
/timestamp > SAMPLING_TIME/
{
    trunc(@compile_time_top, TOP_RESULTS_COUNT);
    trunc(@compile_time_last, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", walltimestamp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d methods with largest compilation time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printa("%10@d %s::%s%s\n", @compile_time_top);

    printf(
        "\nTop %d methods with smallest compilation time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printa("%10@d %s::%s%s\n", @compile_time_last);

    printf("\n");
    printf("Compiled methods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Loaded compiled methods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unoaded compiled methods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFailed compilation:\n");
    printa("%10@d %s::%s%s\n", @fail_compile_count);

    SAMPLING_TIME = timestamp + INTERVAL_SECS * 1000000000ull;
}

:::END
{
    trunc(@compile_time_top, TOP_RESULTS_COUNT);
    trunc(@compile_time_last, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", walltimestamp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d methods with largest compilation time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printa("%10@d %s::%s%s\n", @compile_time_top);

    printf(
        "\nTop %d methods with smallest compilation time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printa("%10@d %s::%s%s\n", @compile_time_last);

    printf("\n");
    printf("Compiled methods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Loaded compiled methods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unoaded compiled methods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFailed compilations:\n");
    printa("%10@d %s::%s%s\n", @fail_compile_count);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscall::rexit:entry,
syscall::exit:entry
/pid == $target/
{
   exit(0);
}
