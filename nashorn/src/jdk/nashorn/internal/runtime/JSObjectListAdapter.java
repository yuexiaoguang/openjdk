package jdk.nashorn.internal.runtime;

import jdk.nashorn.api.scripting.JSObject;

/**
 * A ListAdapter that can wraps a JSObject.
 */
public final class JSObjectListAdapter extends ListAdapter {
    /**
     * Creates a new list wrapper for the specified JSObject.
     * @param obj JSOcript the object to wrap
     */
    public JSObjectListAdapter(final JSObject obj) {
        super(obj);
    }

    @Override
    public int size() {
        return JSType.toInt32(((JSObject)obj).getMember("length"));
    }

    @Override
    protected Object getAt(int index) {
        return ((JSObject)obj).getSlot(index);
    }

    @Override
    protected void setAt(int index, Object element) {
        ((JSObject)obj).setSlot(index, element);
    }
}
