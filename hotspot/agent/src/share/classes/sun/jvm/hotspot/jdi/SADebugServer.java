package sun.jvm.hotspot.jdi;

public final class SADebugServer {
   // do not allow instance creation
   private SADebugServer() {}

   private static void usage() {
      java.io.PrintStream out = System.out;
      out.println("Usage: jsadebugd [options] <pid> [server-id]");
      out.println("\t\t(to connect to a live java process)");
      out.println("   or  jsadebugd [options] <executable> <core> [server-id]");
      out.println("\t\t(to connect to a core file produced by <executable>)");
      out.println("\t\tserver-id is an optional unique id for this debug server, needed ");
      out.println("\t\tif multiple debug servers are run on the same machine");
      out.println("where options include:");
      out.println("   -h | -help\tto print this help message");
      System.exit(1);
  }

   public static void main(String[] args) {
      if ((args.length < 1) || (args.length > 3)) {
         usage();
      }

      // Attempt to handle "-h" or "-help"
      if (args[0].startsWith("-")) {
         usage();
      }

      // By default SA agent classes prefer Windows process debugger
      // to windbg debugger. SA expects special properties to be set
      // to choose other debuggers. We will set those here before
      // attaching to SA agent.

       System.setProperty("sun.jvm.hotspot.debugger.useWindbgDebugger", "true");

      // delegate to the actual SA debug server.
      sun.jvm.hotspot.DebugServer.main(args);
   }
}
