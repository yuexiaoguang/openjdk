
#ifndef OS_LINUX_VM_INTERFACESUPPORT_LINUX_HPP
#define OS_LINUX_VM_INTERFACESUPPORT_LINUX_HPP

// Contains inlined functions for class InterfaceSupport

static inline void serialize_memory(JavaThread *thread) {
  os::write_memory_serialize_page(thread);
}

#endif // OS_LINUX_VM_INTERFACESUPPORT_LINUX_HPP
