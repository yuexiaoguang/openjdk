package com.sun.xml.internal.ws.api.pipe;

import javax.xml.ws.WebServiceFeature;

/**
 * Feature used to request starting a fiber synchronous to the calling
 * thread but allowing it to later switch to run asynchronously to that thread.
 */
public class SyncStartForAsyncFeature
  extends WebServiceFeature {

  public SyncStartForAsyncFeature() {
    enabled = true;
  }

  @Override
  public String getID() {
    return SyncStartForAsyncFeature.class.getSimpleName();
  }
}
