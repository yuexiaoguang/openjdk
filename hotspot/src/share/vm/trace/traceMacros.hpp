#ifndef SHARE_VM_TRACE_TRACE_MACRO_HPP
#define SHARE_VM_TRACE_TRACE_MACRO_HPP

#define EVENT_THREAD_EXIT(thread)
#define EVENT_THREAD_DESTRUCT(thread)

#define TRACE_INIT_ID(k)
#define TRACE_DATA TraceThreadData

#define TRACE_START() JNI_OK
#define TRACE_INITIALIZE() JNI_OK

#define TRACE_DEFINE_KLASS_METHODS typedef int ___IGNORED_hs_trace_type1
#define TRACE_DEFINE_KLASS_TRACE_ID typedef int ___IGNORED_hs_trace_type2
#define TRACE_DEFINE_OFFSET typedef int ___IGNORED_hs_trace_type3
#define TRACE_ID_OFFSET in_ByteSize(0); ShouldNotReachHere()
#define TRACE_TEMPLATES(template)
#define TRACE_INTRINSICS(do_intrinsic, do_class, do_name, do_signature, do_alias)

#endif
