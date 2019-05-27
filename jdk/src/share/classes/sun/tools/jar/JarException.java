package sun.tools.jar;

import java.io.IOException;

public
class JarException extends IOException {

    static final long serialVersionUID = -4351820108009811497L;

    public JarException() {
        super();
    }

    public JarException(String s) {
        super(s);
    }
}
