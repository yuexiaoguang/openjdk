
#ifndef OS_BSD_VM_INTERFACESUPPORT_BSD_HPP
#define OS_BSD_VM_INTERFACESUPPORT_BSD_HPP

// Contains inlined functions for class InterfaceSupport

static inline void serialize_memory(JavaThread *thread) {
  os::write_memory_serialize_page(thread);
}

#endif // OS_BSD_VM_INTERFACESUPPORT_BSD_HPP
