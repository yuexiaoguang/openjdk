package com.sun.org.apache.xml.internal.resolver.helpers;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;

/**
 * Static method for dealing with file: URLs.
 *
 * <p>This class defines a static method that can be used to construct
 * an appropriate file: URL from parts. It's defined here so that it
 * can be reused throught the resolver.</p>
 *
 * <p>(Yes, I'd rather have called this class FileUR<b>I</b>, but
 * given that a jave.net.URL is returned, it seemed...even more
 * confusing.)</p>
 */
public abstract class FileURL {
  protected FileURL() { }

  /**
   * Construct a file: URL for a path name.
   *
   * <p>URLs in the file: scheme can be constructed for paths on
   * the local file system. Several possibilities need to be considered:
   * </p>
   *
   * <ul>
   * <li>If the path does not begin with a slash, then it is assumed
   * to reside in the users current working directory
   * (System.getProperty("user.dir")).</li>
   * <li>On Windows machines, the current working directory uses
   * backslashes (\\, instead of /).</li>
   * <li>If the current working directory is "/", don't add an extra
   * slash before the base name.</li>
   * </ul>
   *
   * <p>This method is declared static so that other classes
   * can use it directly.</p>
   *
   * @param pathname The path name component for which to construct a URL.
   *
   * @return The appropriate file: URL.
   *
   * @throws MalformedURLException if the pathname can't be turned into
   *         a proper URL.
   */
  public static URL makeURL(String pathname) throws MalformedURLException {
    /*if (pathname.startsWith("/")) {
      return new URL("file://" + pathname);
    }

    String userdir = System.getProperty("user.dir");
    userdir.replace('\\', '/');

    if (userdir.endsWith("/")) {
      return new URL("file:///" + userdir + pathname);
    } else {
      return new URL("file:///" + userdir + "/" + pathname);
    }
     */
      File file = new File(pathname);
      return file.toURI().toURL();
  }
}
