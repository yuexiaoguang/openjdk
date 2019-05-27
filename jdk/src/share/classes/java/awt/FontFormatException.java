package java.awt;

/**
 * Thrown by method createFont in the <code>Font</code> class to indicate
 * that the specified font is bad.
 */
public
class FontFormatException extends Exception {
    /*
     * serialVersionUID
     */
    private static final long serialVersionUID = -4481290147811361272L;

    /**
     * Report a FontFormatException for the reason specified.
     * @param reason a <code>String</code> message indicating why
     *        the font is not accepted.
     */
    public FontFormatException(String reason) {
      super (reason);
    }
}
