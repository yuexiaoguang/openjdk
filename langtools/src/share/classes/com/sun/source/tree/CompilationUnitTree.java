package com.sun.source.tree;

import java.util.List;
import javax.tools.JavaFileObject;

/**
 * Represents the abstract syntax tree for compilation units (source
 * files) and package declarations (package-info.java).
 */
@jdk.Exported
public interface CompilationUnitTree extends Tree {
    List<? extends AnnotationTree> getPackageAnnotations();
    ExpressionTree getPackageName();
    List<? extends ImportTree> getImports();
    List<? extends Tree> getTypeDecls();
    JavaFileObject getSourceFile();

    /**
     * Gets the line map for this compilation unit, if available.
     * Returns null if the line map is not available.
     * @return the line map for this compilation unit
     */
    LineMap getLineMap();
}
