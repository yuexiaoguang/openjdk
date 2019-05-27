package sun.awt.X11;

/*
 * The listener interface for receiving "interesting" for XFileDialogPeer
 * choice events (opening, closing).
 * The listener added by means of the method addXChoicePeerListener
 * A opening choice event is generated when the invoking unfurledChoice.toFront()
 * A closing choice event is generated at the time of the processing the mouse releasing
 * and the Enter pressing.
 * see 6240074 for more information
 */
interface XChoicePeerListener{
    public void unfurledChoiceOpening(ListHelper choiceHelper);
    public void unfurledChoiceClosing();
}
