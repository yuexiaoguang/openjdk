package sun.jvm.hotspot.debugger.win32.coff;

/** Models the "sstSymbols" subsection in Visual C++ 5.0 debug
    information. Public symbols from these subsections are moved to
    the sstGlobalSym subsection during packing, and remaining symbols
    are moved to the sstAlignSym subsection. For this reason this
    subsection contains no accessors. */
public interface DebugVC50SSSymbols extends DebugVC50Subsection {
}
