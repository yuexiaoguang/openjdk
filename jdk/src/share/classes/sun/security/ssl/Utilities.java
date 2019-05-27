package sun.security.ssl;

import javax.net.ssl.*;
import java.util.*;
import sun.net.util.IPAddressUtil;

/**
 * A utility class to share the static methods.
 */
final class Utilities {
    /**
     * Puts {@code hostname} into the {@code serverNames} list.
     * <P>
     * If the {@code serverNames} does not look like a legal FQDN, it will
     * not be put into the returned list.
     * <P>
     * Note that the returned list does not allow duplicated name type.
     *
     * @return a list of {@link SNIServerName}
     */
    static List<SNIServerName> addToSNIServerNameList(
            List<SNIServerName> serverNames, String hostname) {

        SNIHostName sniHostName = rawToSNIHostName(hostname);
        if (sniHostName == null) {
            return serverNames;
        }

        int size = serverNames.size();
        List<SNIServerName> sniList = (size != 0) ?
                new ArrayList<SNIServerName>(serverNames) :
                new ArrayList<SNIServerName>(1);

        boolean reset = false;
        for (int i = 0; i < size; i++) {
            SNIServerName serverName = sniList.get(i);
            if (serverName.getType() == StandardConstants.SNI_HOST_NAME) {
                sniList.set(i, sniHostName);
                if (Debug.isOn("ssl")) {
                    System.out.println(Thread.currentThread().getName() +
                        ", the previous server name in SNI (" + serverName +
                        ") was replaced with (" + sniHostName + ")");
                }
                reset = true;
                break;
            }
        }

        if (!reset) {
            sniList.add(sniHostName);
        }

        return Collections.<SNIServerName>unmodifiableList(sniList);
    }

    /**
     * Converts string hostname to {@code SNIHostName}.
     * <P>
     * Note that to check whether a hostname is a valid domain name, we cannot
     * use the hostname resolved from name services.  For virtual hosting,
     * multiple hostnames may be bound to the same IP address, so the hostname
     * resolved from name services is not always reliable.
     *
     * @param  hostname
     *         the raw hostname
     * @return an instance of {@link SNIHostName}, or null if the hostname does
     *         not look like a FQDN
     */
    private static SNIHostName rawToSNIHostName(String hostname) {
        SNIHostName sniHostName = null;
        if (hostname != null && hostname.indexOf('.') > 0 &&
                !hostname.endsWith(".") &&
                !IPAddressUtil.isIPv4LiteralAddress(hostname) &&
                !IPAddressUtil.isIPv6LiteralAddress(hostname)) {

            try {
                sniHostName = new SNIHostName(hostname);
            } catch (IllegalArgumentException iae) {
                // don't bother to handle illegal host_name
                if (Debug.isOn("ssl")) {
                    System.out.println(Thread.currentThread().getName() +
                        ", \"" + hostname + "\" " +
                        "is not a legal HostName for  server name indication");
                }
            }
        }

        return sniHostName;
    }
}
