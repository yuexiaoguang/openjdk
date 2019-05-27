package sun.net.smtp;

import java.io.IOException;

/**
 * This exception is thrown when unexpected results are returned during
 * an SMTP session.
 */
public class SmtpProtocolException extends IOException {
    private static final long serialVersionUID = -7547136771133814908L;

    SmtpProtocolException(String s) {
        super(s);
    }
}
