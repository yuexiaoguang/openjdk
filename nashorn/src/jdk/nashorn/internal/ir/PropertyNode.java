package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation of an object literal property.
 */
@Immutable
public final class PropertyNode extends Node {

    /** Property key. */
    private final PropertyKey key;

    /** Property value. */
    private final Expression value;

    /** Property getter. */
    private final FunctionNode getter;

    /** Property getter. */
    private final FunctionNode setter;

    /**
     * Constructor
     *
     * @param token   token
     * @param finish  finish
     * @param key     the key of this property
     * @param value   the value of this property
     * @param getter  getter function body
     * @param setter  setter function body
     */
    public PropertyNode(final long token, final int finish, final PropertyKey key, final Expression value, final FunctionNode getter, final FunctionNode setter) {
        super(token, finish);
        this.key    = key;
        this.value  = value;
        this.getter = getter;
        this.setter = setter;
    }

    private PropertyNode(final PropertyNode propertyNode, final PropertyKey key, final Expression value, final FunctionNode getter, final FunctionNode setter) {
        super(propertyNode);
        this.key    = key;
        this.value  = value;
        this.getter = getter;
        this.setter = setter;
    }

    /**
     * Get the name of the property key
     * @return key name
     */
    public String getKeyName() {
        return key.getPropertyName();
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterPropertyNode(this)) {
            return visitor.leavePropertyNode(
                setKey((PropertyKey)((Node)key).accept(visitor)).
                setValue(value == null ? null : (Expression)value.accept(visitor)).
                setGetter(getter == null ? null : (FunctionNode)getter.accept(visitor)).
                setSetter(setter == null ? null : (FunctionNode)setter.accept(visitor)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        if (value instanceof FunctionNode && ((FunctionNode)value).getIdent() != null) {
            value.toString(sb);
        }

        if (value != null) {
            ((Node)key).toString(sb);
            sb.append(": ");
            value.toString(sb);
        }

        if (getter != null) {
            sb.append(' ');
            getter.toString(sb);
        }

        if (setter != null) {
            sb.append(' ');
            setter.toString(sb);
        }
    }

    /**
     * Get the getter for this property
     * @return getter or null if none exists
     */
    public FunctionNode getGetter() {
        return getter;
    }

    /**
     * Set the getter of this property, null if none
     * @param getter getter
     * @return same node or new node if state changed
     */
    public PropertyNode setGetter(final FunctionNode getter) {
        if (this.getter == getter) {
            return this;
        }
        return new PropertyNode(this, key, value, getter, setter);
    }

    /**
     * Return the key for this property node
     * @return the key
     */
    public Expression getKey() {
        return (Expression)key;
    }

    private PropertyNode setKey(final PropertyKey key) {
        if (this.key == key) {
            return this;
        }
        return new PropertyNode(this, key, value, getter, setter);
    }

    /**
     * Get the setter for this property
     * @return setter or null if none exists
     */
    public FunctionNode getSetter() {
        return setter;
    }

    /**
     * Set the setter for this property, null if none
     * @param setter setter
     * @return same node or new node if state changed
     */
    public PropertyNode setSetter(final FunctionNode setter) {
        if (this.setter == setter) {
            return this;
        }
        return new PropertyNode(this, key, value, getter, setter);
    }

    /**
     * Get the value of this property
     * @return property value
     */
    public Expression getValue() {
        return value;
    }

    /**
     * Set the value of this property
     * @param value new value
     * @return same node or new node if state changed
     */
    public PropertyNode setValue(final Expression value) {
        if (this.value == value) {
            return this;
        }
        return new PropertyNode(this, key, value, getter, setter);
   }
}
