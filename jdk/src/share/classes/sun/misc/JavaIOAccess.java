package sun.misc;

import java.io.Console;
import java.nio.charset.Charset;

public interface JavaIOAccess {
    public Console console();
    public Charset charset();
}
