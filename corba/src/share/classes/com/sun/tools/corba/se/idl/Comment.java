package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Comment
{
  // Styles
  static final int UNKNOWN  = -1;
  static final int JAVA_DOC =  0;
  static final int C_BLOCK  =  1;
  static final int CPP_LINE =  2;

  // System-dependent line separator
  private static String _eol = System.getProperty ("line.separator");

  private String _text  = new String ("");
  private int    _style = UNKNOWN;

  Comment () {_text = new String (""); _style = UNKNOWN;} // ctor

  Comment (String text) {_text = text; _style = style (_text);} // ctor

  /** Sets comment text */
  public void text (String string) {_text = string; _style = style (_text);}

  /** Returns comment text */
  public String text () {return _text;}

  /** Returns the comment style of a string. */
  private int style (String text)
  {
    if (text == null)
      return UNKNOWN;
    else if (text.startsWith ("/**") && text.endsWith ("*/"))
      return JAVA_DOC;
    else if (text.startsWith ("/*") && text.endsWith ("*/"))
      return C_BLOCK;
    else if (text.startsWith ("//"))
      return CPP_LINE;
    else
      return UNKNOWN;
  } // style

  /** Writes comment text to standard output (debug). */
  public void write () {System.out.println (_text);}

  /** Writes comment text to the specified print stream in the appropriate format. */
  public void generate (String indent, PrintWriter printStream)
  {
    if (_text == null || printStream == null)
      return;
    if (indent == null)
      indent = new String ("");
    switch (_style)
    {
      case JAVA_DOC:
        //printJavaDoc (indent, printStream);
        print (indent, printStream);
        break;
      case C_BLOCK:
        //printCBlock (indent, printStream);
        print (indent, printStream);
        break;
      case CPP_LINE:
        //printCppLine (indent, printStream);
        print (indent, printStream);
        break;
      default:
        break;
    }
  } // generate

  /** Writes comment to the specified print stream without altering its format.
      This routine does not alter vertical or horizontal spacing of comment text,
      thus, it only works well for comments with a non-indented first line. */
  private void print (String indent, PrintWriter stream)
  {
    String text = _text.trim () + _eol;
    String line = null;

    int iLineStart = 0;
    int iLineEnd   = text.indexOf (_eol);
    int iTextEnd   = text.length () - 1;

    stream.println ();
    while (iLineStart < iTextEnd)
    {
      line = text.substring (iLineStart, iLineEnd);
      stream.println (indent + line);
      iLineStart = iLineEnd + _eol.length ();
      iLineEnd = iLineStart + text.substring (iLineStart).indexOf (_eol);
    }
  } // print

  /*
   *  The following routines print formatted comments of differing styles.
   *  Each routine will alter the horizontal spacing of the comment text,
   *  but not the vertical spacing.
   */

  /** Writes comment in JavaDoc-style to the specified print stream. */
  private void printJavaDoc (String indent, PrintWriter stream)
  {
    // Strip surrounding "/**", "*/", and whitespace; append sentinel
    String text = _text.substring (3, (_text.length () - 2)).trim () + _eol;
    String line = null;

    int iLineStart = 0;
    int iLineEnd   = text.indexOf (_eol);
    int iTextEnd   = text.length () - 1;   // index of last text character

    stream.println (_eol + indent + "/**");
    while (iLineStart < iTextEnd)
    {
      line = text.substring (iLineStart, iLineEnd).trim ();
      if (line.startsWith ("*"))
        // Strip existing "*<ws>" prefix
        stream.println (indent + " * " + line.substring (1, line.length ()).trim ());
      else
        stream.println (indent + " * " + line);
      iLineStart = iLineEnd + _eol.length ();
      iLineEnd = iLineStart + text.substring (iLineStart).indexOf (_eol);
    }
    stream.println (indent + " */");
  } // printJavaDoc

  /** Writes comment in c-block-style to the specified print stream. */
  private void printCBlock (String indent, PrintWriter stream)
  {
    // Strip surrounding "/*", "*/", and whitespace; append sentinel
    String text = _text.substring (2, (_text.length () - 2)).trim () + _eol;
    String line = null;

    int iLineStart = 0;
    int iLineEnd   = text.indexOf (_eol);
    int iTextEnd   = text.length () - 1;   // index of last text character

    stream.println (indent + "/*");
    while (iLineStart < iTextEnd)
    {
      line = text.substring (iLineStart, iLineEnd).trim ();
      if (line.startsWith ("*"))
        // Strip existing "*[ws]" prefix
        stream.println (indent + " * " + line.substring (1, line.length ()).trim ());
      else
        stream.println (indent + " * " + line);
      iLineStart = iLineEnd + _eol.length ();
      iLineEnd   = iLineStart + text.substring (iLineStart).indexOf (_eol);
    }
    stream.println (indent + " */");
  } // printCBlock

  /** Writes a line comment to the specified print stream. */
  private void printCppLine (String indent, PrintWriter stream)
  {
    stream.println (indent + "//");
    // Strip "//[ws]" prefix
    stream.println (indent + "// " + _text.substring (2).trim ());
    stream.println (indent + "//");
  } // printCppLine
} // class Comment


/*==================================================================================
  DATE<AUTHOR>   ACTION
  ----------------------------------------------------------------------------------
  11aug1997<daz> Initial version completed.
  18aug1997<daz> Modified generate to write comment unformatted.
  ==================================================================================*/
