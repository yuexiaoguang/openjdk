package com.sun.java.browser.net;

public interface ProxyInfo {
    public String   getHost();
    public int      getPort();
    public boolean  isSocks();
}
