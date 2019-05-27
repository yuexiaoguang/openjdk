package org.w3c.dom.html;

/**
 *  Push button. See the  BUTTON element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLButtonElement extends HTMLElement {
    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    public HTMLFormElement getForm();

    /**
     *  A single character access key to give access to the form control. See
     * the  accesskey attribute definition in HTML 4.0.
     */
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    /**
     *  The control is unavailable in this context. See the  disabled
     * attribute definition in HTML 4.0.
     */
    public boolean getDisabled();
    public void setDisabled(boolean disabled);

    /**
     *  Form control or object name when submitted with a form. See the  name
     * attribute definition in HTML 4.0.
     */
    public String getName();
    public void setName(String name);

    /**
     *  Index that represents the element's position in the tabbing order. See
     * the  tabindex attribute definition in HTML 4.0.
     */
    public int getTabIndex();
    public void setTabIndex(int tabIndex);

    /**
     *  The type of button. See the  type attribute definition in HTML 4.0.
     */
    public String getType();

    /**
     *  The current form control value. See the  value attribute definition in
     * HTML 4.0.
     */
    public String getValue();
    public void setValue(String value);

}
