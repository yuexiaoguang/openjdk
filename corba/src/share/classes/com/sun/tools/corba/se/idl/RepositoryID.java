package com.sun.tools.corba.se.idl;

// NOTES:
// -D57110<daz> Add method to verify format (CORBA 2.3).
public class RepositoryID
{
  public RepositoryID ()
  {
    _id = "";
  } // ctor

  public RepositoryID (String id)
  {
    _id = id;
  } // ctor

  public String ID ()
  {
    return _id;
  } // ID

  public Object clone ()
  {
    return new RepositoryID (_id);
  } // clone

  public String toString ()
  {
    return ID ();
  } // toString

  /**
   * Determine is a supplied string meets the minimal format requirement
   * for a Repository ID.
   * @return true iff supplied string has form '<format>:<string>', where
   * <format> is any non-empty string not containing ':'.
   **/
  public static boolean hasValidForm (String string)
  {
    return string != null && string.indexOf (':') > 0;
  } // hasValidForm

  private String _id;
} // class RepositoryID
