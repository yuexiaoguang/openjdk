package com.sun.org.apache.xml.internal.resolver.helpers;

/**
 * Static debugging/messaging class for Catalogs.
 *
 * <p>This class defines a set of static methods that can be called
 * to produce debugging messages. Messages have an associated "debug
 * level" and messages below the current setting are not displayed.</p>
 */
public class Debug {
  /** The internal debug level. */
  protected int debug = 0;

  /** Constructor */
  public Debug() {
    // nop
  }

  /** Set the debug level for future messages. */
  public void setDebug(int newDebug) {
    debug = newDebug;
  }

  /** Get the current debug level. */
  public int getDebug() {
    return debug;
  }

  /**
   * Print debug message (if the debug level is high enough).
   *
   * <p>Prints "the message"</p>
   *
   * @param level The debug level of this message. This message
   * will only be
   * displayed if the current debug level is at least equal to this
   * value.
   * @param message The text of the message.
   */
  public void message(int level, String message) {
    if (debug >= level) {
      System.out.println(message);
    }
  }

  /**
   * Print debug message (if the debug level is high enough).
   *
   * <p>Prints "the message: spec"</p>
   *
   * @param level The debug level of this message. This message
   * will only be
   * displayed if the current debug level is at least equal to this
   * value.
   * @param message The text of the message.
   * @param spec An argument to the message.
   */
  public void message(int level, String message, String spec) {
    if (debug >= level) {
      System.out.println(message + ": " + spec);
    }
  }

  /**
   * Print debug message (if the debug level is high enough).
   *
   * <p>Prints "the message: spec1" and "spec2" indented on the next line.</p>
   *
   * @param level The debug level of this message. This message
   * will only be
   * displayed if the current debug level is at least equal to this
   * value.
   * @param message The text of the message.
   * @param spec1 An argument to the message.
   * @param spec2 Another argument to the message.
   */
  public void message(int level, String message,
                             String spec1, String spec2) {
    if (debug >= level) {
      System.out.println(message + ": " + spec1);
      System.out.println("\t" + spec2);
    }
  }
}
