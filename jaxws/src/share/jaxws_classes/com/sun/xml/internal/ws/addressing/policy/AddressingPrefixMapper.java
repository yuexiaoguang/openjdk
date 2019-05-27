package com.sun.xml.internal.ws.addressing.policy;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import com.sun.xml.internal.ws.policy.spi.PrefixMapper;
import com.sun.xml.internal.ws.addressing.W3CAddressingMetadataConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * This supplies the prefixes for the namespaces under Addressing domain.
 */
public class AddressingPrefixMapper implements PrefixMapper {

    private static final Map<String, String> prefixMap = new HashMap<String, String>();

    static {
        prefixMap.put(AddressingVersion.MEMBER.policyNsUri, "wsap");
        prefixMap.put(AddressingVersion.MEMBER.nsUri, "wsa");
        prefixMap.put(W3CAddressingMetadataConstants.WSAM_NAMESPACE_NAME,W3CAddressingMetadataConstants.WSAM_PREFIX_NAME);
    }

    public Map<String, String> getPrefixMap() {
        return prefixMap;
    }

}
