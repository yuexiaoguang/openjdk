package com.sun.tools.corba.se.idl;

import java.io.IOException;

class NoPragma extends PragmaHandler
{
  public boolean process (String pragma, String currentToken) throws IOException
  {
    parseException (Util.getMessage ("Preprocessor.unknownPragma", pragma));
    skipToEOL ();
    return true;
  } // process
} // class NoPragma
