package com.sun.tools.doclets.formats.html.markup;

import java.util.Locale;

/**
 * Enum representing HTML tags.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public enum HtmlTag {
    A(BlockType.INLINE, EndTag.END),
    BLOCKQUOTE,
    BODY(BlockType.OTHER, EndTag.END),
    BR(BlockType.INLINE, EndTag.NOEND),
    CAPTION,
    CENTER,
    CODE(BlockType.INLINE, EndTag.END),
    DD,
    DIR,
    DIV,
    DL,
    DT,
    EM(BlockType.INLINE, EndTag.END),
    FONT(BlockType.INLINE, EndTag.END),
    FRAME(BlockType.OTHER, EndTag.NOEND),
    FRAMESET(BlockType.OTHER, EndTag.END),
    H1,
    H2,
    H3,
    H4,
    H5,
    H6,
    HEAD(BlockType.OTHER, EndTag.END),
    HR(BlockType.BLOCK, EndTag.NOEND),
    HTML(BlockType.OTHER, EndTag.END),
    I(BlockType.INLINE, EndTag.END),
    IMG(BlockType.INLINE, EndTag.NOEND),
    LI,
    LISTING,
    LINK(BlockType.OTHER, EndTag.NOEND),
    MENU,
    META(BlockType.OTHER, EndTag.NOEND),
    NOFRAMES(BlockType.OTHER, EndTag.END),
    NOSCRIPT(BlockType.OTHER, EndTag.END),
    OL,
    P,
    PRE,
    SCRIPT(BlockType.OTHER, EndTag.END),
    SMALL(BlockType.INLINE, EndTag.END),
    SPAN(BlockType.INLINE, EndTag.END),
    STRONG(BlockType.INLINE, EndTag.END),
    SUB(BlockType.INLINE, EndTag.END),
    TABLE,
    TBODY,
    TD,
    TH,
    TITLE(BlockType.OTHER, EndTag.END),
    TR,
    TT(BlockType.INLINE, EndTag.END),
    UL;

    public final BlockType blockType;
    public final EndTag endTag;
    public final String value;

    /**
     * Enum representing the type of HTML element.
     */
    public static enum BlockType {
        BLOCK,
        INLINE,
        OTHER;
    }

    /**
     * Enum representing HTML end tag requirement.
     */
    public static enum EndTag {
        END,
        NOEND;
    }

    HtmlTag() {
        this(BlockType.BLOCK, EndTag.END);
    }

    HtmlTag(BlockType blockType, EndTag endTag ) {
        this.blockType = blockType;
        this.endTag = endTag;
        this.value = name().toLowerCase(Locale.US);
    }

    /**
     * Returns true if the end tag is required. This is specific to the standard
     * doclet and does not exactly resemble the W3C specifications.
     *
     * @return true if end tag needs to be displayed else return false
     */
    public boolean endTagRequired() {
        return (endTag == EndTag.END);
    }

    public String toString() {
        return value;
    }
}
