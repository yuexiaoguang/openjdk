package com.sun.hotspot.tools.compiler;

import java.io.PrintStream;
import java.util.*;

public interface LogEvent {
    public double getStart();

    public double getElapsedTime();

    public Compilation getCompilation();

    public void print(PrintStream stream);
}
