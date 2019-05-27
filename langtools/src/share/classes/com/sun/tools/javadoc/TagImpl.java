package com.sun.tools.javadoc;

import com.sun.javadoc.*;

/**
 * Represents a documentation tag, e.g. @since, @author, @version.
 * Given a tag (e.g. "@since 1.2"), holds tag name (e.g. "@since")
 * and tag text (e.g. "1.2").  TagImpls with structure or which require
 * special processing are handled by subclasses (ParamTagImpl, SeeTagImpl,
 * and ThrowsTagImpl
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
class TagImpl implements Tag {

    protected final String text;
    protected final String name;
    protected final DocImpl holder;

    /**
     * Cached first sentence.
     */
    private Tag[] firstSentence;

    /**
     * Cached inline tags.
     */
    private Tag[] inlineTags;

    /**
     *  Constructor
     */
    TagImpl(DocImpl holder, String name, String text) {
        this.holder = holder;
        this.name = name;
        this.text = text;
    }

    /**
     * Return the name of this tag.
     */
    public String name() {
        return name;
    }

    /**
     * Return the containing {@link Doc} of this Tag element.
     */
    public Doc holder() {
        return holder;
    }

    /**
     * Return the kind of this tag.
     */
    public String kind() {
        return name;
    }

    /**
     * Return the text of this tag, that is, portion beyond tag name.
     */
    public String text() {
        return text;
    }

    DocEnv docenv() {
        return holder.env;
    }

    /**
     * for use by subclasses which have two part tag text.
     */
    String[] divideAtWhite() {
        String[] sa = new String[2];
        int len = text.length();
        // if no white space found
        sa[0] = text;
        sa[1] = "";
        for (int inx = 0; inx < len; ++inx) {
            char ch = text.charAt(inx);
            if (Character.isWhitespace(ch)) {
                sa[0] = text.substring(0, inx);
                for (; inx < len; ++inx) {
                    ch = text.charAt(inx);
                    if (!Character.isWhitespace(ch)) {
                        sa[1] = text.substring(inx, len);
                        break;
                    }
                }
                break;
            }
        }
        return sa;
    }

    /**
     * convert this object to a string.
     */
    public String toString() {
        return name + ":" + text;
    }

    /**
     * For documentation comment with embedded @link tags, return the array of
     * TagImpls consisting of SeeTagImpl(s) and text containing TagImpl(s).
     * Within a comment string "This is an example of inline tags for a
     * documentation comment {@link Doc {@link Doc commentlabel}}",
     * where inside the inner braces, the first "Doc" carries exctly the same
     * syntax as a SeeTagImpl and the second "commentlabel" is label for the Html
     * Link, will return an array of TagImpl(s) with first element as TagImpl with
     * comment text "This is an example of inline tags for a documentation
     * comment" and second element as SeeTagImpl with referenced class as "Doc"
     * and the label for the Html Link as "commentlabel".
     *
     * @return TagImpl[] Array of tags with inline SeeTagImpls.
     * @see ParamTagImpl
     * @see ThrowsTagImpl
     */
    public Tag[] inlineTags() {
        if (inlineTags == null) {
            inlineTags = Comment.getInlineTags(holder, text);
        }
        return inlineTags;
    }

    /**
     * Return array of tags for the first sentence in the doc comment text.
     */
    public Tag[] firstSentenceTags() {
        if (firstSentence == null) {
            //Parse all sentences first to avoid duplicate warnings.
            inlineTags();
            try {
                docenv().setSilent(true);
                firstSentence = Comment.firstSentenceTags(holder, text);
            } finally {
                docenv().setSilent(false);
            }
        }
        return firstSentence;
    }

    /**
     * Return the doc item to which this tag is attached.
     * @return the doc item to which this tag is attached.
     */
    public SourcePosition position() {
        return holder.position();
    }
}
