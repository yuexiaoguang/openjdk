/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.regexp.internal;

import com.sun.org.apache.regexp.internal.RECompiler;
import com.sun.org.apache.regexp.internal.RESyntaxException;

/**
 * 'recompile' is a command line tool that pre-compiles one or more regular expressions
 * for use with the regular expression matcher class 'RE'.  For example, the command
 * "java recompile a*b" produces output like this:
 *
 * <pre>
 *
 *    // Pre-compiled regular expression "a*b"
 *    char[] re1Instructions =
 *    {
 *        0x007c, 0x0000, 0x001a, 0x007c, 0x0000, 0x000d, 0x0041,
 *        0x0001, 0x0004, 0x0061, 0x007c, 0x0000, 0x0003, 0x0047,
 *        0x0000, 0xfff6, 0x007c, 0x0000, 0x0003, 0x004e, 0x0000,
 *        0x0003, 0x0041, 0x0001, 0x0004, 0x0062, 0x0045, 0x0000,
 *        0x0000,
 *    };
 *
 *    REProgram re1 = new REProgram(re1Instructions);
 *
 * </pre>
 *
 * By pasting this output into your code, you can construct a regular expression matcher
 * (RE) object directly from the pre-compiled data (the character array re1), thus avoiding
 * the overhead of compiling the expression at runtime.  For example:
 *
 * <pre>
 *
 *    RE r = new RE(re1);
 *
 * </pre>
 */
public class recompile
{
    /**
     * Main application entrypoint.
     * @param arg Command line arguments
     */
    static public void main(String[] arg)
    {
        // Create a compiler object
        RECompiler r = new RECompiler();

        // Print usage if arguments are incorrect
        if (arg.length <= 0 || arg.length % 2 != 0)
        {
            System.out.println("Usage: recompile <patternname> <pattern>");
            System.exit(0);
        }

        // Loop through arguments, compiling each
        for (int i = 0; i < arg.length; i += 2)
        {
            try
            {
                // Compile regular expression
                String name         = arg[i];
                String pattern      = arg[i+1];
                String instructions = name + "PatternInstructions";

                // Output program as a nice, formatted character array
                System.out.print("\n    // Pre-compiled regular expression '" + pattern + "'\n"
                                 + "    private static char[] " + instructions + " = \n    {");

                // Compile program for pattern
                REProgram program = r.compile(pattern);

                // Number of columns in output
                int numColumns = 7;

                // Loop through program
                char[] p = program.getInstructions();
                for (int j = 0; j < p.length; j++)
                {
                    // End of column?
                    if ((j % numColumns) == 0)
                    {
                        System.out.print("\n        ");
                    }

                    // Print character as padded hex number
                    String hex = Integer.toHexString(p[j]);
                    while (hex.length() < 4)
                    {
                        hex = "0" + hex;
                    }
                    System.out.print("0x" + hex + ", ");
                }

                // End of program block
                System.out.println("\n    };");
                System.out.println("\n    private static RE " + name + "Pattern = new RE(new REProgram(" + instructions + "));");
            }
            catch (RESyntaxException e)
            {
                System.out.println("Syntax error in expression \"" + arg[i] + "\": " + e.toString());
            }
            catch (Exception e)
            {
                System.out.println("Unexpected exception: " + e.toString());
            }
            catch (Error e)
            {
                System.out.println("Internal error: " + e.toString());
            }
        }
    }
}
