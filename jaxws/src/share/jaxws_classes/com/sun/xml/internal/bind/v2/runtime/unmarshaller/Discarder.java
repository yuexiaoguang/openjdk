package com.sun.xml.internal.bind.v2.runtime.unmarshaller;

/**
 * {@link Loader} implementation that discards the whole sub-tree.
 *
 * Mostly used for recovering fom errors.
 */
public final class Discarder extends Loader {
    public static final Loader INSTANCE = new Discarder();

    private Discarder() {
        super(false);
    }

    @Override
    public void childElement(UnmarshallingContext.State state, TagName ea) {
        state.target = null;
        // registering this allows the discarder to process the whole subtree.
        state.loader = this;
    }
}
