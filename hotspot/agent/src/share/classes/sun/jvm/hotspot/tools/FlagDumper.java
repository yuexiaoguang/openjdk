package sun.jvm.hotspot.tools;

import java.io.PrintStream;
import sun.jvm.hotspot.debugger.JVMDebugger;
import sun.jvm.hotspot.runtime.*;

public class FlagDumper extends Tool {

    public FlagDumper() {
        super();
    }

    public FlagDumper(JVMDebugger d) {
        super(d);
    }

   public void run() {
      VM.Flag[] flags = VM.getVM().getCommandLineFlags();
      PrintStream out = System.out;
      if (flags == null) {
         out.println("Command Flags info not available! (use 1.4.1_03 or later)");
      } else {
         for (int f = 0; f < flags.length; f++) {
            out.print(flags[f].getName());
            out.print(" = ");
            out.println(flags[f].getValue());
         }
      }
   }

   public static void main(String[] args) {
      FlagDumper fd = new FlagDumper();
      fd.execute(args);
   }
}
