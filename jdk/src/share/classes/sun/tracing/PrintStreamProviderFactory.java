package sun.tracing;

import java.lang.reflect.Method;
import java.io.PrintStream;
import java.util.HashMap;

import com.sun.tracing.ProviderFactory;
import com.sun.tracing.Provider;
import com.sun.tracing.ProviderName;
import com.sun.tracing.Probe;
import com.sun.tracing.ProbeName;

/**
 * Factory class to create tracing Providers.
 *
 * This factory will create tracing instances that print to a PrintStream
 * when activated.
 */
public class PrintStreamProviderFactory extends ProviderFactory {

    private PrintStream stream;

    public PrintStreamProviderFactory(PrintStream stream) {
        this.stream = stream;
    }

    public <T extends Provider> T createProvider(Class<T> cls) {
        PrintStreamProvider provider = new PrintStreamProvider(cls, stream);
        provider.init();
        return provider.newProxyInstance();
    }
}

class PrintStreamProvider extends ProviderSkeleton {

    private PrintStream stream;
    private String providerName;

    protected ProbeSkeleton createProbe(Method m) {
        String probeName = getAnnotationString(m, ProbeName.class, m.getName());
        return new PrintStreamProbe(this, probeName, m.getParameterTypes());
    }

    PrintStreamProvider(Class<? extends Provider> type, PrintStream stream) {
        super(type);
        this.stream = stream;
        this.providerName = getProviderName();
    }

    PrintStream getStream() {
        return stream;
    }

    String getName() {
        return providerName;
    }
}

class PrintStreamProbe extends ProbeSkeleton {

    private PrintStreamProvider provider;
    private String name;

    PrintStreamProbe(PrintStreamProvider p, String name, Class<?>[] params) {
        super(params);
        this.provider = p;
        this.name = name;
    }

    public boolean isEnabled() {
        return true;
    }

    public void uncheckedTrigger(Object[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append(provider.getName());
        sb.append(".");
        sb.append(name);
        sb.append("(");
        boolean first = true;
        for (Object o : args) {
            if (first == false) {
                sb.append(",");
            } else {
                first = false;
            }
            sb.append(o.toString());
        }
        sb.append(")");
        provider.getStream().println(sb.toString());
    }
}

