package sun.security.krb5;

import sun.security.krb5.internal.Krb5;
import sun.security.krb5.internal.KRBError;

public class KrbException extends Exception {

    private static final long serialVersionUID = -4993302876451928596L;

    private int returnCode;
    private KRBError error;

    public KrbException(String s) {
        super(s);
    }

    public KrbException(Throwable cause) {
        super(cause);
    }

    public KrbException(int i) {
        returnCode = i;
    }

    public KrbException(int i, String s) {
        this(s);
        returnCode = i;
    }

    public KrbException(KRBError e) {
        returnCode = e.getErrorCode();
        error = e;
    }

    public KrbException(KRBError e, String s) {
        this(s);
        returnCode = e.getErrorCode();
        error = e;
    }

    public KRBError getError() {
        return error;
    }


    public int returnCode() {
        return returnCode;
    }

    public String returnCodeSymbol() {
        return returnCodeSymbol(returnCode);
    }

    public static String returnCodeSymbol(int i) {
        return "not yet implemented";
    }

    public String returnCodeMessage() {
        return Krb5.getErrorMessage(returnCode);
    }

    public static String errorMessage(int i) {
        return Krb5.getErrorMessage(i);
    }


    public String krbErrorMessage() {
        StringBuffer strbuf = new StringBuffer("krb_error " + returnCode);
        String msg =  getMessage();
        if (msg != null) {
            strbuf.append(" ");
            strbuf.append(msg);
        }
        return strbuf.toString();
    }

    /**
     * Returns messages like:
     * "Integrity check on decrypted field failed (31) - \
     *                         Could not decrypt service ticket"
     * If the error code is 0 then the first half is skipped.
     */
    public String getMessage() {
        StringBuffer message = new StringBuffer();
        int returnCode = returnCode();
        if (returnCode != 0) {
            message.append(returnCodeMessage());
            message.append(" (").append(returnCode()).append(')');
        }
        String consMessage = super.getMessage();
        if (consMessage != null && consMessage.length() != 0) {
            if (returnCode != 0)
                message.append(" - ");
            message.append(consMessage);
        }
        return message.toString();
    }

    public String toString() {
        return ("KrbException: " + getMessage());
    }

    @Override public int hashCode() {
        int result = 17;
        result = 37 * result + returnCode;
        if (error != null) {
            result = 37 * result + error.hashCode();
        }
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof KrbException)) {
            return false;
        }

        KrbException other = (KrbException)obj;
        if (returnCode != other.returnCode) {
            return false;
        }
        return (error == null)?(other.error == null):
            (error.equals(other.error));
    }
}
