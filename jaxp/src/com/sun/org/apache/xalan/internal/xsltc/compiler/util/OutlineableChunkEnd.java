/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.Instruction;
/**
 * <p>Marks the end of a region of byte code that can be copied into a new
 * method.  See the {@link OutlineableChunkStart} pseudo-instruction for
 * details.</p>
 */
class OutlineableChunkEnd extends MarkerInstruction {
    /**
     * A constant instance of {@link OutlineableChunkEnd}.  As it has no fields,
     * there should be no need to create an instance of this class.
     */
    public static final Instruction OUTLINEABLECHUNKEND =
                                                new OutlineableChunkEnd();

    /**
     * Private default constructor.  As it has no fields,
     * there should be no need to create an instance of this class.  See
     * {@link OutlineableChunkEnd#OUTLINEABLECHUNKEND}.
     */
    private OutlineableChunkEnd() {
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String getName() {
        return OutlineableChunkEnd.class.getName();
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String toString() {
        return getName();
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String toString(boolean verbose) {
        return getName();
    }
}
