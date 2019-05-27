package sun.jvmstat.monitor;

import java.net.*;

/**
 * An abstraction that identifies a target Java Virtual Machine.
 * The VmIdentifier, or vmid, provides a convenient string representation
 * of the information needed to locate and communicate with a target
 * Java Virtual Machine. The string, based on a {@link URI}, may specify
 * the communications protocol, host name, local vm identifier, and protocol
 * specific information for a target Java Virtual Machine. The format for
 * a VmIdentifier string is:
 * <pre>
 *      [<I>protocol</I>:][<I>//</I>]<I><B>lvmid</B></I>[<I>@hostname</I>][<I>:port</I>][<I>/servername</I>]
 * </pre>
 * The only required component of this string is the Local Virtual Machine
 * Identifier, or <tt>lvmid</tt>, which uniquely identifies the target
 * Java Virtual Machine on a host. The optional components of the VmIdentifier
 * include:
 * <ul>
 *   <li><p><tt>protocol</tt> - The communications protocol. A VmIdentifier
 *          omitting the protocol must be resolved against a HostIdentifier
 *          using {@link HostIdentifier#resolve}.
 *       </p></li>
 *   <li><p><tt>hostname</tt> - A hostname or IP address indicating the target
 *          host. A VmIdentifier omitting the protocol must be resolved
 *          against a HostIdentifier using {@link HostIdentifier#resolve}.
 *       </p></li>
 *   <li><p><tt>port</tt> - The port for the communications protocol.
 *          Treatment of the <tt>port</tt> parameter is implementation
 *          (protocol) specific. A VmIdentifier omitting the protocol should
 *          be resolved against a HostIdentifier using
 *          {@link HostIdentifier#resolve}.
 *       </p></li>
 *   <li><p><tt>servername</tt> - The treatment of the Path, Query, and
 *          Fragment components of the VmIdentifier are implementation
 *          (protocol) dependent. A VmIdentifier omitting the protocol should
 *          be resolved against a HostIdentifier using
 *          {@link HostIdentifier#resolve}.
 *       </p></li>
 * </ul>
 * <p>
 * All VmIdentifier instances are constructed as absolute, hierarchical URIs.
 * The constructors will accept relative (and even some malformed,
 * though convenient) URI strings. Such strings are transformed into
 * legitimate, absolute URI strings.
 * </p>
 * <p>
 * With the exception of <em>file:</em> based VmIdentifier strings, all
 * VmIdentifier strings must include a <tt>lvmid</tt>. Attempting to construct
 * a non-file based VmIdentifier that doesn't include a <tt>lvmid</tt>
 * component will result in a <tt>MonitorException</tt>.
 * </p>
 * <p>
 * Here are some examples of VmIdentifier strings.
 * <ul>
 *    <li><p>Relative URIs</p></li>
 *      <ul>
 *         <li><p><em>1234</em> - Specifies the Java Virtual Machine
 *                identified by lvmid <em>1234</em> on an unnamed host.
 *                This string is transformed into the absolute form
 *                <em>//1234</em>, which must be resolved against a
 *                HostIdentifier.
 *         </p></li>
 *         <li><p><em>1234@hostname</em> - Specifies the Java Virtual
 *                Machine identified by lvmid <em>1234</em> on host
 *                <em>hostname</em> with an unnamed protocol.
 *                This string is transformed into the absolute form
 *                <em>//1234@hostname</em>, which must be resolved against
 *                a HostIdentifier.
 *         </p></li>
 *         <li><p><em>1234@hostname:2099</em> - Specifies the Java Virtual
 *                Machine identified by lvmid <em>1234</em> on host
 *                <em>hostname</em> with an unnamed protocol, but with
 *                port <em>2099</em>. This string is transformed into
 *                the absolute form <em>//1234@hostname:2099</em>, which
 *                must be resolved against a HostIdentifier.
 *         </p></li>
 *      </ul>
 *    <li><p>Absolute URIs</p></li>
 *      <ul>
 *         <li><p><em>rmi://1234@hostname:2099/remoteobjectname</em> -
 *                Specifies the Java Virtual Machine identified by lvmid
 *                <em>1234</em> on host <em>hostname</em> accessed
 *                using the <em>rmi:</em> protocol through the rmi remote
 *                object named <em>remoteobjectname</em> as registered with
 *                the <em>rmiserver</em> on port <em>2099</em> on host
 *                <em>hostname</em>.
 *         </p></li>
 *         <li><p><em>file:/path/file</em> - Identifies a Java Virtual Machine
 *                through accessing a special file based protocol to use as
 *                the communications mechanism.
 *         </p></li>
 *      </ul>
 * </ul>
 * </p>
 */
public class VmIdentifier {
    private URI uri;

    /**
     * creates a canonical representation of the uriString. This method
     * performs certain translations depending on the type of URI generated
     * by the string.
     */
    private URI canonicalize(String uriString) throws URISyntaxException {
        if (uriString == null) {
            uriString = "local://0@localhost";
            return new URI(uriString);
        }

        URI u = new URI(uriString);

        if (u.isAbsolute()) {
            if (u.isOpaque()) {
                /*
                 * rmi:1234@hostname/path#fragment converted to
                 * rmi://1234@hostname/path#fragment
                 */
                u = new URI(u.getScheme(), "//" + u.getSchemeSpecificPart(),
                            u.getFragment());
            }
        } else {
            /*
             * make the uri absolute, if possible. A relative URI doesn't
             * specify the scheme part, so it's safe to prepend a "//" and
             * try again.
             */
            if (!uriString.startsWith("//")) {
                if (u.getFragment() == null) {
                    u = new URI("//" + u.getSchemeSpecificPart());
                } else {
                    u = new URI("//" + u.getSchemeSpecificPart() + "#"
                                + u.getFragment());
                }
            }
        }
        return u;
    }

    /**
     * check that the VmIdentifier includes a unique numerical identifier
     * for the target JVM.
     */
    private void validate() throws URISyntaxException {
        // file:// uri, which is a special case where the lvmid is not required.
        String s = getScheme();
        if ((s != null) && (s.compareTo("file") == 0)) {
            return;
        }
        if (getLocalVmId() == -1) {
            throw new URISyntaxException(uri.toString(), "Local vmid required");
        }
    }

    /**
     * Create a VmIdentifier instance from a string value.
     *
     * @param uriString a string representing a target Java Virtual Machine.
     *                  The syntax of the string must conforms to the rules
     *                  specified in the class documentation.
     * @throws URISyntaxException Thrown when the uriString or its canonical
     *                            form is poorly formed.
     */
    public VmIdentifier(String uriString) throws URISyntaxException {
        URI u;
        try {
            u = canonicalize(uriString);
        } catch (URISyntaxException e) {
            /*
             * a vmid of the form 1234@hostname:1098 causes an exception,
             * so try again with a leading "//"
             */
            if (uriString.startsWith("//")) {
                throw e;
            }
            u = canonicalize("//"+uriString);
        }

        uri = u;

        // verify that we have a valid lvmid
        validate();
    }

    /**
     * Create a VmIdentifier instance from a URI object.
     *
     * @param uri a well formed, absolute URI indicating the
     *            target Java Virtual Machine.
     * @throws URISyntaxException Thrown if the URI is missing some
     *                            required component.
     */
    public VmIdentifier(URI uri) throws URISyntaxException {
        this.uri = uri;
        validate();
    }

    /**
     * Return the corresponding HostIdentifier for this VmIdentifier.
     * <p>
     * This method constructs a HostIdentifier object from the VmIdentifier.
     * If the VmIdentifier is not specific about the protocol or other
     * components of the URI, then the resulting HostIdentifier will
     * be constructed based on this missing information. Typically, the
     * missing components will have result in the HostIdentifier assigning
     * assumed defaults that allow the VmIdentifier to be resolved according
     * to those defaults.
     * </p>
     * <p>
     * For example, a VmIdentifier that specifies only a <tt>lvmid</tt>
     * will result in a HostIdentifier for <em>localhost</em> utilizing
     * the default local protocol, <em>local:</em>. A VmIdentifier that
     * specifies both a <tt>vmid</tt> and a <tt>hostname</tt> will result
     * in a HostIdentifier for the specified host with the default remote
     * protocol, <em>rmi:</em>, using the protocol defaults for the
     * <tt>port</tt> and <tt>servername</tt> components.
     * </p>
     *
     * @return HostIdentifier - the host identifier for the host containing
     *                          the Java Virtual Machine represented by this
     *                          VmIdentifier.
     * @throws URISyntaxException Thrown if a bad host URI is constructed.
     *                            This exception may get encapsulated into
     *                            a MonitorException in a future version.
     */
    public HostIdentifier getHostIdentifier() throws URISyntaxException {
        StringBuffer sb = new StringBuffer();
        if (getScheme() != null) {
            sb.append(getScheme()).append(":");
        }
        sb.append("//").append(getHost());
        if (getPort() != -1) {
            sb.append(":").append(getPort());
        }
        if (getPath() != null) {
            sb.append(getPath());
        }
        return new HostIdentifier(sb.toString());
    }

    /**
     * Return the Scheme, or protocol, portion of this VmIdentifier.
     *
     * @return String - the scheme for this VmIdentifier.
     * @see URI#getScheme()
     */
    public String getScheme() {
        return uri.getScheme();
    }

    /**
     * Return the Scheme Specific Part of this VmIdentifier.
     *
     * @return String - the Scheme Specific Part for this VmIdentifier.
     * @see URI#getSchemeSpecificPart()
     */
    public String getSchemeSpecificPart() {
        return uri.getSchemeSpecificPart();
    }

    /**
     * Return the UserInfo part of this VmIdentifier.
     *
     * @return String - the UserInfo part for this VmIdentifier.
     * @see URI#getUserInfo()
     */
    public String getUserInfo() {
        return uri.getUserInfo();
    }

    /**
     * Return the Host part of this VmIdentifier.
     *
     * @return String - the Host part for this VmIdentifier.
     * @see URI#getHost()
     */
    public String getHost() {
        return uri.getHost();
    }

    /**
     * Return the Port part of this VmIdentifier.
     *
     * @return int - the Port part for this VmIdentifier.
     * @see URI#getPort()
     */
    public int getPort() {
        return uri.getPort();
    }

    /**
     * Return the Authority part of this VmIdentifier.
     *
     * @return String - the Authority part for this VmIdentifier.
     * @see URI#getAuthority()
     */
    public String getAuthority() {
        return uri.getAuthority();
    }

    /**
     * Return the Path part of this VmIdentifier.
     *
     * @return String - the Path part for this VmIdentifier.
     * @see URI#getPath()
     */
    public String getPath() {
        return uri.getPath();
    }

    /**
     * Return the Query part of this VmIdentifier.
     *
     * @return String - the Query part for this VmIdentifier.
     * @see URI#getQuery()
     */
    public String getQuery() {
        return uri.getQuery();
    }

    /**
     * Return the Fragment part of this VmIdentifier.
     *
     * @return String - the Fragment part for this VmIdentifier.
     * @see URI#getFragment()
     */
    public String getFragment() {
        return uri.getFragment();
    }

    /**
     * Return the Local Virtual Machine Identifier for this VmIdentifier.
     * The Local Virtual Machine Identifier is also known as the
     * <em>lvmid</em>.
     *
     * @return int - the lvmid for this VmIdentifier.
     */
    public int getLocalVmId() {
        int result = -1;
        try {
            if (uri.getUserInfo() == null) {
                result = Integer.parseInt(uri.getAuthority());
            } else {
                result = Integer.parseInt(uri.getUserInfo());
            }
        } catch (NumberFormatException e) { }
        return result;
    }

    /**
     * Return the mode indicated in this VmIdentifier.
     *
     * @return String - the mode string. If no mode is specified, then "r"
     *                  is returned. otherwise, the specified mode is returned.
     */
    public String getMode() {
        String query = getQuery();
        if (query != null) {
            String[] queryArgs = query.split("\\+");
            for (int i = 0; i < queryArgs.length; i++) {
                if (queryArgs[i].startsWith("mode=")) {
                    int index = queryArgs[i].indexOf('=');
                    return queryArgs[i].substring(index+1);
                }
            }
        }
        return "r";
    }

    /**
     * Return the URI associated with the VmIdentifier.
     *
     * @return URI - the URI.
     * @see URI
     */
    public URI getURI() {
        return uri;
    }

    /**
     * Return the hash code for this VmIdentifier. The hash code is
     * identical to the hash code for the contained URI.
     *
     * @return int - the hashcode.
     * @see URI#hashCode()
     */
    public int hashCode() {
        return uri.hashCode();
    }

    /**
     * Test for quality with other objects.
     *
     * @param object the object to be test for equality.
     * @return boolean - returns true if the given object is of type
     *                   VmIdentifier and its URI field is equal to
     *                   this object's URI field. Otherwise, return false.
     *
     * @see URI#equals(Object)
     */
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof VmIdentifier)) {
            return false;
        }
        return uri.equals(((VmIdentifier)object).uri);
    }

    /**
     * Convert to a string representation. Conversion is identical to
     * calling getURI().toString(). This may change in a future release.
     *
     * @return String - a String representation of the VmIdentifier.
     *
     * @see URI#toString()
     */
    public String toString() {
        return uri.toString();
    }
}
