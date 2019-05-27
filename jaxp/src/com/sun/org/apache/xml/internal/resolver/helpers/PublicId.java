package com.sun.org.apache.xml.internal.resolver.helpers;

/**
 * Static methods for dealing with public identifiers.
 *
 * <p>This class defines a set of static methods that can be called
 * to handle public identifiers.</p>
 */
public abstract class PublicId {
  protected PublicId() { }

  /**
   * Normalize a public identifier.
   *
   * <p>Public identifiers must be normalized according to the following
   * rules before comparisons between them can be made:</p>
   *
   * <ul>
   * <li>Whitespace characters are normalized to spaces (e.g., line feeds,
   * tabs, etc. become spaces).</li>
   * <li>Leading and trailing whitespace is removed.</li>
   * <li>Multiple internal whitespaces are normalized to a single
   * space.</li>
   * </ul>
   *
   * <p>This method is declared static so that other classes
   * can use it directly.</p>
   *
   * @param publicId The unnormalized public identifier.
   *
   * @return The normalized identifier.
   */
  public static String normalize(String publicId) {
    String normal = publicId.replace('\t', ' ');
    normal = normal.replace('\r', ' ');
    normal = normal.replace('\n', ' ');
    normal = normal.trim();

    int pos;

    while ((pos = normal.indexOf("  ")) >= 0) {
      normal = normal.substring(0, pos) + normal.substring(pos+1);
    }

    return normal;
  }

  /**
   * Encode a public identifier as a "publicid" URN.
   *
   * <p>This method is declared static so that other classes
   * can use it directly.</p>
   *
   * @param publicId The unnormalized public identifier.
   *
   * @return The normalized identifier.
   */
  public static String encodeURN(String publicId) {
    String urn = PublicId.normalize(publicId);

    urn = PublicId.stringReplace(urn, "%", "%25");
    urn = PublicId.stringReplace(urn, ";", "%3B");
    urn = PublicId.stringReplace(urn, "'", "%27");
    urn = PublicId.stringReplace(urn, "?", "%3F");
    urn = PublicId.stringReplace(urn, "#", "%23");
    urn = PublicId.stringReplace(urn, "+", "%2B");
    urn = PublicId.stringReplace(urn, " ", "+");
    urn = PublicId.stringReplace(urn, "::", ";");
    urn = PublicId.stringReplace(urn, ":", "%3A");
    urn = PublicId.stringReplace(urn, "//", ":");
    urn = PublicId.stringReplace(urn, "/", "%2F");

    return "urn:publicid:" + urn;
  }

  /**
   * Decode a "publicid" URN into a public identifier.
   *
   * <p>This method is declared static so that other classes
   * can use it directly.</p>
   *
   * @param urn The urn:publicid: URN
   *
   * @return The normalized identifier.
   */
  public static String decodeURN(String urn) {
    String publicId = "";

    if (urn.startsWith("urn:publicid:")) {
      publicId = urn.substring(13);
    } else {
      return urn;
    }

    publicId = PublicId.stringReplace(publicId, "%2F", "/");
    publicId = PublicId.stringReplace(publicId, ":", "//");
    publicId = PublicId.stringReplace(publicId, "%3A", ":");
    publicId = PublicId.stringReplace(publicId, ";", "::");
    publicId = PublicId.stringReplace(publicId, "+", " ");
    publicId = PublicId.stringReplace(publicId, "%2B", "+");
    publicId = PublicId.stringReplace(publicId, "%23", "#");
    publicId = PublicId.stringReplace(publicId, "%3F", "?");
    publicId = PublicId.stringReplace(publicId, "%27", "'");
    publicId = PublicId.stringReplace(publicId, "%3B", ";");
    publicId = PublicId.stringReplace(publicId, "%25", "%");

    return publicId;
    }

  /**
   * Replace one string with another.
   *
   */
  private static String stringReplace(String str,
                                      String oldStr,
                                      String newStr) {

    String result = "";
    int pos = str.indexOf(oldStr);

    //    System.out.println(str + ": " + oldStr + " => " + newStr);

    while (pos >= 0) {
      //      System.out.println(str + " (" + pos + ")");
      result += str.substring(0, pos);
      result += newStr;
      str = str.substring(pos+1);

      pos = str.indexOf(oldStr);
    }

    return result + str;
  }
}
