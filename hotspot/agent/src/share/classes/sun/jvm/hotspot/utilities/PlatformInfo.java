package sun.jvm.hotspot.utilities;

/** Provides canonicalized OS and CPU information for the rest of the
    system. */
public class PlatformInfo {
  /* Returns "solaris" if on Solaris; "win32" if Windows; "linux" if
     Linux. Used to determine location of dbx and import module, or
     possible debugger agent on win32. */
  public static String getOS() throws UnsupportedPlatformException {
    String os = System.getProperty("os.name");
    if (os.equals("SunOS")) {
      return "solaris";
    } else if (os.equals("Linux")) {
      return "linux";
    } else if (os.equals("FreeBSD")) {
      return "bsd";
    } else if (os.equals("NetBSD")) {
      return "bsd";
    } else if (os.equals("OpenBSD")) {
      return "bsd";
    } else if (os.contains("Darwin") || os.contains("OS X")) {
      return "darwin";
    } else if (os.startsWith("Windows")) {
      return "win32";
    } else {
      throw new UnsupportedPlatformException("Operating system " + os + " not yet supported");
    }
  }

  /* Returns "sparc" for SPARC based platforms and "x86" for x86 based
     platforms. Otherwise returns the value of os.arch.  If the value
     is not recognized as supported, an exception is thrown instead. */
  public static String getCPU() throws UnsupportedPlatformException {
    String cpu = System.getProperty("os.arch");
    if (cpu.equals("i386") || cpu.equals("x86")) {
      return "x86";
    } else if (cpu.equals("sparc") || cpu.equals("sparcv9")) {
      return "sparc";
    } else if (cpu.equals("ia64") || cpu.equals("amd64") || cpu.equals("x86_64")) {
      return cpu;
    } else {
      try {
        Class pic = Class.forName("sun.jvm.hotspot.utilities.PlatformInfoClosed");
        AltPlatformInfo api = (AltPlatformInfo)pic.newInstance();
        if (api.knownCPU(cpu)) {
          return cpu;
        }
      } catch (Exception e) {}
      throw new UnsupportedPlatformException("CPU type " + cpu + " not yet supported");
    }
  }

  // this main is invoked from Makefile to make platform specific agent Makefile(s).
  public static void main(String[] args) {
    System.out.println(getOS());
  }
}
