package sun.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.management.ObjectName;

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.VMOption;

/**
 * Implementation of the diagnostic MBean for Hotspot VM.
 */
public class HotSpotDiagnostic implements HotSpotDiagnosticMXBean {
    public HotSpotDiagnostic() {
    }

    public void dumpHeap(String outputFile, boolean live) throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(outputFile);
            Util.checkControlAccess();
        }

        dumpHeap0(outputFile, live);
    }

    private native void dumpHeap0(String outputFile, boolean live) throws IOException;

    public List<VMOption> getDiagnosticOptions() {
        List<Flag> allFlags = Flag.getAllFlags();
        List<VMOption> result = new ArrayList<>();
        for (Flag flag : allFlags) {
            if (flag.isWriteable() && flag.isExternal()) {
                result.add(flag.getVMOption());
            }
        }
        return result;
    }

    public VMOption getVMOption(String name) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        Flag f = Flag.getFlag(name);
        if (f == null) {
            throw new IllegalArgumentException("VM option \"" +
                name + "\" does not exist");
        }
        return f.getVMOption();
    }

    public void setVMOption(String name, String value) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        if (value == null) {
            throw new NullPointerException("value cannot be null");
        }

        Util.checkControlAccess();
        Flag flag = Flag.getFlag(name);
        if (flag == null) {
            throw new IllegalArgumentException("VM option \"" +
                name + "\" does not exist");
        }
        if (!flag.isWriteable()){
            throw new IllegalArgumentException("VM Option \"" +
                name + "\" is not writeable");
        }

        // Check the type of the value
        Object v = flag.getValue();
        if (v instanceof Long) {
            try {
                long l = Long.parseLong(value);
                Flag.setLongValue(name, l);
            } catch (NumberFormatException e) {
                IllegalArgumentException iae =
                    new IllegalArgumentException("Invalid value:" +
                        " VM Option \"" + name + "\"" +
                        " expects numeric value");
                iae.initCause(e);
                throw iae;
            }
        } else if (v instanceof Boolean) {
            if (!value.equalsIgnoreCase("true") &&
                !value.equalsIgnoreCase("false")) {
                throw new IllegalArgumentException("Invalid value:" +
                    " VM Option \"" + name + "\"" +
                    " expects \"true\" or \"false\".");
            }
            Flag.setBooleanValue(name, Boolean.parseBoolean(value));
        } else if (v instanceof String) {
            Flag.setStringValue(name, value);
        } else {
            throw new IllegalArgumentException("VM Option \"" +
                name + "\" is of an unsupported type: " +
                v.getClass().getName());
        }
    }

    public ObjectName getObjectName() {
        return Util.newObjectName("com.sun.management:type=HotSpotDiagnostic");
    }
}
