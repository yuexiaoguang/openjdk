/**
 * This package provides the {@code javax.script} integration, which is the preferred way to use Nashorn.
 * You will ordinarily do this to obtain an instance of a Nashorn script engine:
 * <pre>
 * import javax.script.*;
 * ...
 * ScriptEngine nashornEngine = new ScriptEngineManager().getEngineByName("Nashorn");
 * </pre>
 * <p>Nashorn script engines implement the optional {@link javax.script.Invocable} and {@link javax.script.Compilable}
 * interfaces, allowing for efficient pre-compilation and repeated execution of scripts. See
 * {@link jdk.nashorn.api.scripting.NashornScriptEngineFactory} for further details.
 */
package jdk.nashorn.api.scripting;
