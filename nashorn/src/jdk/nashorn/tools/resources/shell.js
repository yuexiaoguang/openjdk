/*
 * Initialization script for shell when running in interactive mode.
 */

/**
 * Reads zero or more lines from standard input and returns concatenated string
 *
 * @param endMarker marker string that signals end of input
 * @param prompt prompt printed for each line
 */
Object.defineProperty(this, "input", {
    value: function input(endMarker, prompt) {
        if (!endMarker) {
            endMarker = "";
        }

        if (!prompt) {
            prompt = " >> ";
        }

        var imports = new JavaImporter(java.io, java.lang);
        var str = "";
        with (imports) {
            var reader = new BufferedReader(new InputStreamReader(System['in']));
            var line;
            while (true) {
                System.out.print(prompt);
                line = reader.readLine();
                if (line == null || line == endMarker) {
                    break;
                }
                str += line + "\n";
            }
        }

        return str;
    },
    enumerable: false,
    writable: true,
    configurable: true
});


/**
 * Reads zero or more lines from standard input and evaluates the concatenated
 * string as code
 *
 * @param endMarker marker string that signals end of input
 * @param prompt prompt printed for each line
 */
Object.defineProperty(this, "evalinput", {
    value: function evalinput(endMarker, prompt) {
        var code = input(endMarker, prompt);
        // make sure everything is evaluated in global scope!
        return this.eval(code);
    },
    enumerable: false,
    writable: true,
    configurable: true
});
