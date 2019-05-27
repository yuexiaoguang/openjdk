package sun.security.x509;

/**
 * CertException indicates one of a variety of certificate problems.
 * @deprecated use one of the Exceptions defined in the
 * java.security.cert package.
 */
@Deprecated
class CertParseError extends CertException
{
    private static final long serialVersionUID = -4559645519017017804L;

    CertParseError (String where)
    {
        super (CertException.verf_PARSE_ERROR, where);
    }
}
