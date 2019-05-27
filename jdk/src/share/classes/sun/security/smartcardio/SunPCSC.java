package sun.security.smartcardio;

import java.security.*;

import javax.smartcardio.*;

/**
 * Provider object for PC/SC.
 */
public final class SunPCSC extends Provider {

    private static final long serialVersionUID = 6168388284028876579L;

    public SunPCSC() {
        super("SunPCSC", 1.8d, "Sun PC/SC provider");
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                put("TerminalFactory.PC/SC", "sun.security.smartcardio.SunPCSC$Factory");
                return null;
            }
        });
    }

    public static final class Factory extends TerminalFactorySpi {
        public Factory(Object obj) throws PCSCException {
            if (obj != null) {
                throw new IllegalArgumentException
                    ("SunPCSC factory does not use parameters");
            }
            // make sure PCSC is available and that we can obtain a context
            PCSC.checkAvailable();
            PCSCTerminals.initContext();
        }
        /**
         * Returns the available readers.
         * This must be a new object for each call.
         */
        protected CardTerminals engineTerminals() {
            return new PCSCTerminals();
        }
    }

}
