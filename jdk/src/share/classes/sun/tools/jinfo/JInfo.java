package sun.tools.jinfo;

import java.lang.reflect.Method;
import java.io.IOException;
import java.io.InputStream;

import com.sun.tools.attach.VirtualMachine;
import sun.tools.attach.HotSpotVirtualMachine;

/*
 * This class is the main class for the JInfo utility. It parses its arguments
 * and decides if the command should be satisfied using the VM attach mechanism
 * or an SA tool. At this time the only option that uses the VM attach
 * mechanism is the -flag option to set or print a command line option of a
 * running application. All other options are mapped to SA tools.
 */
public class JInfo {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage(1); // no arguments
        }

        boolean useSA = true;
        String arg1 = args[0];
        if (arg1.startsWith("-")) {
            if (arg1.equals("-flags") ||
                arg1.equals("-sysprops")) {
                // SA JInfo needs <pid> or <server> or
                // (<executable> and <code file>). So, total
                // argument count including option has to 2 or 3.
                if (args.length != 2 && args.length != 3) {
                    usage(1);
                }
            } else if (arg1.equals("-flag")) {
                // do not use SA, use attach-on-demand
                useSA = false;
            } else {
                // unknown option or -h or -help, print help
                int exit;
                if (arg1.equals("-help") || arg1.equals("-h")) {
                    exit = 0;
                } else {
                    exit = 1;
                }
                usage(exit);
            }
        }

        if (useSA) {
            runTool(args);
        } else {
            if (args.length == 3) {
                String pid = args[2];
                String option = args[1];
                flag(pid, option);
            } else {
                int exit;
                if (arg1.equals("-help") || arg1.equals("-h")) {
                    exit = 0;
                } else {
                    exit = 1;
                }
                usage(exit);
            }
        }
    }

    // Invoke SA tool  with the given arguments
    private static void runTool(String args[]) throws Exception {
        String tool = "sun.jvm.hotspot.tools.JInfo";
        // Tool not available on this  platform.
        Class<?> c = loadClass(tool);
        if (c == null) {
            usage(1);
        }

        // invoke the main method with the arguments
        Class[] argTypes = { String[].class } ;
        Method m = c.getDeclaredMethod("main", argTypes);

        Object[] invokeArgs = { args };
        m.invoke(null, invokeArgs);
    }

    // loads the given class using the system class loader
    private static Class<?> loadClass(String name) {
        //
        // We specify the system clas loader so as to cater for development
        // environments where this class is on the boot class path but sa-jdi.jar
        // is on the system class path. Once the JDK is deployed then both
        // tools.jar and sa-jdi.jar are on the system class path.
        //
        try {
            return Class.forName(name, true,
                                 ClassLoader.getSystemClassLoader());
        } catch (Exception x)  { }
        return null;
    }

    private static void flag(String pid, String option) throws IOException {
        VirtualMachine vm = attach(pid);
        String flag;
        InputStream in;
        int index = option.indexOf('=');
        if (index != -1) {
            flag = option.substring(0, index);
            String value = option.substring(index + 1);
            in = ((HotSpotVirtualMachine)vm).setFlag(flag, value);
        } else {
            char c = option.charAt(0);
            switch (c) {
                case '+':
                    flag = option.substring(1);
                    in = ((HotSpotVirtualMachine)vm).setFlag(flag, "1");
                    break;
                case '-':
                    flag = option.substring(1);
                    in = ((HotSpotVirtualMachine)vm).setFlag(flag, "0");
                    break;
                default:
                    flag = option;
                    in = ((HotSpotVirtualMachine)vm).printFlag(flag);
                    break;
            }
        }

        drain(vm, in);
    }

    // Attach to <pid>, exiting if we fail to attach
    private static VirtualMachine attach(String pid) {
        try {
            return VirtualMachine.attach(pid);
        } catch (Exception x) {
            String msg = x.getMessage();
            if (msg != null) {
                System.err.println(pid + ": " + msg);
            } else {
                x.printStackTrace();
            }
            System.exit(1);
            return null; // keep compiler happy
        }
    }

    // Read the stream from the target VM until EOF, then detach
    private static void drain(VirtualMachine vm, InputStream in) throws IOException {
        // read to EOF and just print output
        byte b[] = new byte[256];
        int n;
        do {
            n = in.read(b);
            if (n > 0) {
                String s = new String(b, 0, n, "UTF-8");
                System.out.print(s);
            }
        } while (n > 0);
        in.close();
        vm.detach();
    }


    // print usage message
    private static void usage(int exit) {

        Class<?> c = loadClass("sun.jvm.hotspot.tools.JInfo");
        boolean usageSA = (c != null);

        System.err.println("Usage:");
        if (usageSA) {
            System.err.println("    jinfo [option] <pid>");
            System.err.println("        (to connect to running process)");
            System.err.println("    jinfo [option] <executable <core>");
            System.err.println("        (to connect to a core file)");
            System.err.println("    jinfo [option] [server_id@]<remote server IP or hostname>");
            System.err.println("        (to connect to remote debug server)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    -flag <name>         to print the value of the named VM flag");
            System.err.println("    -flag [+|-]<name>    to enable or disable the named VM flag");
            System.err.println("    -flag <name>=<value> to set the named VM flag to the given value");
            System.err.println("    -flags               to print VM flags");
            System.err.println("    -sysprops            to print Java system properties");
            System.err.println("    <no option>          to print both of the above");
            System.err.println("    -h | -help           to print this help message");
        } else {
            System.err.println("    jinfo <option> <pid>");
            System.err.println("       (to connect to a running process)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    -flag <name>         to print the value of the named VM flag");
            System.err.println("    -flag [+|-]<name>    to enable or disable the named VM flag");
            System.err.println("    -flag <name>=<value> to set the named VM flag to the given value");
            System.err.println("    -h | -help           to print this help message");
        }

        System.exit(exit);
    }
}
