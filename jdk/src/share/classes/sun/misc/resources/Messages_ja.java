package sun.misc.resources;

/**
 * <p> This class represents the <code>ResourceBundle</code>
 * for sun.misc.
 */
public class Messages_ja extends java.util.ListResourceBundle {

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     * <p>
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
        { "optpkg.versionerror", "\u30A8\u30E9\u30FC: JAR\u30D5\u30A1\u30A4\u30EB{0}\u3067\u7121\u52B9\u306A\u30D0\u30FC\u30B8\u30E7\u30F3\u5F62\u5F0F\u304C\u4F7F\u7528\u3055\u308C\u3066\u3044\u307E\u3059\u3002\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u308B\u30D0\u30FC\u30B8\u30E7\u30F3\u5F62\u5F0F\u306B\u3064\u3044\u3066\u306E\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u3092\u53C2\u7167\u3057\u3066\u304F\u3060\u3055\u3044\u3002" },
        { "optpkg.attributeerror", "\u30A8\u30E9\u30FC: \u5FC5\u8981\u306AJAR\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5C5E\u6027{0}\u304CJAR\u30D5\u30A1\u30A4\u30EB{1}\u306B\u8A2D\u5B9A\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002" },
        { "optpkg.attributeserror", "\u30A8\u30E9\u30FC: \u8907\u6570\u306E\u5FC5\u8981\u306AJAR\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5C5E\u6027\u304CJAR\u30D5\u30A1\u30A4\u30EB{0}\u306B\u8A2D\u5B9A\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002" }
    };

}
