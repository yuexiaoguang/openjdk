package sun.security.krb5.internal;

import sun.security.krb5.EncryptionKey;
import java.util.BitSet;

public class AuthContext {
    public HostAddress remoteAddress;
    public int remotePort;
    public HostAddress localAddress;
    public int localPort;
    public EncryptionKey keyBlock;
    public EncryptionKey localSubkey;
    public EncryptionKey remoteSubkey;
    public BitSet authContextFlags;
    public int remoteSeqNumber;
    public int localSeqNumber;
    public Authenticator authenticator;
    public int reqCksumType;
    public int safeCksumType;
    public byte[] initializationVector;
    //public ReplayCache replayCache;
};
