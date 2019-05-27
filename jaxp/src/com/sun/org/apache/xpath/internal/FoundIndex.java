package com.sun.org.apache.xpath.internal;

/**
 * Class to let us know when it's time to do
 * a search from the parent because of indexing.
 */
public class FoundIndex extends RuntimeException
{
    static final long serialVersionUID = -4643975335243078270L;

  /**
   * Constructor FoundIndex
   *
   */
  public FoundIndex(){}
}
