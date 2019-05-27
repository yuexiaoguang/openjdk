
#include "precompiled.hpp"
#include "runtime/os.hpp"
#include "vm_version_sparc.hpp"

static bool detect_niagara() {
  char cpu[128];
  bool rv = false;

  FILE* fp = fopen("/proc/cpuinfo", "r");
  if (fp == NULL) {
    return rv;
  }

  while (!feof(fp)) {
    if (fscanf(fp, "cpu\t\t: %100[^\n]", &cpu) == 1) {
      if (strstr(cpu, "Niagara") != NULL) {
        rv = true;
      }
      break;
    }
  }

  fclose(fp);

  return rv;
}

int VM_Version::platform_features(int features) {
  // Default to generic v9
  features = generic_v9_m;

  if (detect_niagara()) {
    NOT_PRODUCT(if (PrintMiscellaneous && Verbose) tty->print_cr("Detected Linux on Niagara");)
    features = niagara1_m;
  }

  return features;
}
