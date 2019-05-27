#include "precompiled.hpp"
#include "oops/oop.inline.hpp"
#include "utilities/accessFlags.hpp"
#ifdef TARGET_OS_FAMILY_linux
# include "os_linux.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_solaris
# include "os_solaris.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_windows
# include "os_windows.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_bsd
# include "os_bsd.inline.hpp"
#endif


void AccessFlags::atomic_set_bits(jint bits) {
  // Atomically update the flags with the bits given
  jint old_flags, new_flags, f;
  do {
    old_flags = _flags;
    new_flags = old_flags | bits;
    f = Atomic::cmpxchg(new_flags, &_flags, old_flags);
  } while(f != old_flags);
}

void AccessFlags::atomic_clear_bits(jint bits) {
  // Atomically update the flags with the bits given
  jint old_flags, new_flags, f;
  do {
    old_flags = _flags;
    new_flags = old_flags & ~bits;
    f = Atomic::cmpxchg(new_flags, &_flags, old_flags);
  } while(f != old_flags);
}

#if !defined(PRODUCT) || INCLUDE_JVMTI

void AccessFlags::print_on(outputStream* st) const {
  if (is_public      ()) st->print("public "      );
  if (is_private     ()) st->print("private "     );
  if (is_protected   ()) st->print("protected "   );
  if (is_static      ()) st->print("static "      );
  if (is_final       ()) st->print("final "       );
  if (is_synchronized()) st->print("synchronized ");
  if (is_volatile    ()) st->print("volatile "    );
  if (is_transient   ()) st->print("transient "   );
  if (is_native      ()) st->print("native "      );
  if (is_interface   ()) st->print("interface "   );
  if (is_abstract    ()) st->print("abstract "    );
  if (is_strict      ()) st->print("strict "      );
  if (is_synthetic   ()) st->print("synthetic "   );
  if (is_old         ()) st->print("{old} "       );
  if (is_obsolete    ()) st->print("{obsolete} "  );
  if (on_stack       ()) st->print("{on_stack} "  );
}

#endif // !PRODUCT || INCLUDE_JVMTI

void accessFlags_init() {
  assert(sizeof(AccessFlags) == sizeof(jint), "just checking size of flags");
}
