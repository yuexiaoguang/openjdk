package com.sun.java.browser.net;

import java.net.URL;

public interface ProxyServiceProvider {
    public ProxyInfo[] getProxyInfo(URL url);
}
