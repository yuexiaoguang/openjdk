package sun.security.krb5.internal.ccache;

/**
 * Constants used by file-based credential cache classes.
 */
public interface FileCCacheConstants {
    /*
     * FCC version 2 contains type information for principals.  FCC
     * version 1 does not.
     *
     * FCC version 3 contains keyblock encryption type information, and is
     * architecture independent.  Previous versions are not. */
    public final int KRB5_FCC_FVNO_1 = 0x501;
    public final int KRB5_FCC_FVNO_2 = 0x502;
    public final int KRB5_FCC_FVNO_3 = 0x503;
    public final int KRB5_FCC_FVNO_4 = 0x504;
    public final int FCC_TAG_DELTATIME = 1;
    public final int KRB5_NT_UNKNOWN = 0;
    public final int MAXNAMELENGTH = 1024;
    public final int TKT_FLG_FORWARDABLE = 0x40000000;
    public final int TKT_FLG_FORWARDED  =  0x20000000;
    public final int TKT_FLG_PROXIABLE   = 0x10000000;
    public final int TKT_FLG_PROXY        = 0x08000000;
    public final int TKT_FLG_MAY_POSTDATE  = 0x04000000;
    public final int TKT_FLG_POSTDATED     = 0x02000000;
    public final int TKT_FLG_INVALID        = 0x01000000;
    public final int TKT_FLG_RENEWABLE     = 0x00800000;
    public final int TKT_FLG_INITIAL       = 0x00400000;
    public final int TKT_FLG_PRE_AUTH      = 0x00200000;
    public final int TKT_FLG_HW_AUTH       = 0x00100000;
}
