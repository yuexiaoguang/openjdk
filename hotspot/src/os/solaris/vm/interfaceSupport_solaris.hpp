
#ifndef OS_SOLARIS_VM_INTERFACESUPPORT_SOLARIS_HPP
#define OS_SOLARIS_VM_INTERFACESUPPORT_SOLARIS_HPP

// Contains inlined functions for class InterfaceSupport

static inline void serialize_memory(JavaThread *thread) {
  os::write_memory_serialize_page(thread);
}

#endif // OS_SOLARIS_VM_INTERFACESUPPORT_SOLARIS_HPP
