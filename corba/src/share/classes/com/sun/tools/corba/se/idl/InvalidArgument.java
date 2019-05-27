package com.sun.tools.corba.se.idl;

/**
 * An invalid argument for the compiler has been encountered.
 **/
public class InvalidArgument extends Exception
{
  /** @param arg the invalid argument. */
  public InvalidArgument (String arg)
  {
    message = Util.getMessage ("InvalidArgument.1", arg) + "\n\n" + Util.getMessage ("usage");
  } // ctor

  public InvalidArgument ()
  {
    message = Util.getMessage ("InvalidArgument.2") + "\n\n" + Util.getMessage ("usage");
  } // ctor

  public String getMessage ()
  {
    return message;
  } // getMessage

  private String message = null;
} // class InvalidArgument
