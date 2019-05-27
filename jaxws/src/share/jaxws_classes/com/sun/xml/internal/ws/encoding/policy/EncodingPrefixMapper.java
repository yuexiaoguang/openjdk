package com.sun.xml.internal.ws.encoding.policy;

import com.sun.xml.internal.ws.policy.spi.PrefixMapper;
import java.util.HashMap;
import java.util.Map;

public class EncodingPrefixMapper implements PrefixMapper {

    private static final Map<String, String> prefixMap = new HashMap<String, String>();

    static {
        prefixMap.put(EncodingConstants.ENCODING_NS, "wspe");
        prefixMap.put(EncodingConstants.OPTIMIZED_MIME_NS, "wsoma");
        prefixMap.put(EncodingConstants.SUN_ENCODING_CLIENT_NS, "cenc");
        prefixMap.put(EncodingConstants.SUN_FI_SERVICE_NS, "fi");
    }

    public Map<String, String> getPrefixMap() {
        return prefixMap;
    }

}
