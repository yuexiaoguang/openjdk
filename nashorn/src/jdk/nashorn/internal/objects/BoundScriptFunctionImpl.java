package jdk.nashorn.internal.objects;

import jdk.nashorn.internal.runtime.ScriptFunction;
import jdk.nashorn.internal.runtime.ScriptFunctionData;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.ScriptRuntime;

/**
 * A {@code ScriptFunctionImpl} subclass for functions created using {@code Function.prototype.bind}. Such functions
 * must track their {@code [[TargetFunction]]} property for purposes of correctly implementing {@code [[HasInstance]]};
 * see {@link ScriptFunction#isInstance(ScriptObject)}.
 */
final class BoundScriptFunctionImpl extends ScriptFunctionImpl {
    private final ScriptFunction targetFunction;

    BoundScriptFunctionImpl(ScriptFunctionData data, ScriptFunction targetFunction) {
        super(data, Global.instance());
        setPrototype(ScriptRuntime.UNDEFINED);
        this.targetFunction = targetFunction;
    }

    @Override
    protected ScriptFunction getTargetFunction() {
        return targetFunction;
    }
}
