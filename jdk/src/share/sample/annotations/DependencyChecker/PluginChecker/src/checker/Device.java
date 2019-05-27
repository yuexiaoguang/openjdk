package checker;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents the device configuration. The values are loaded from an XML file by JAXB.
 */
@XmlRootElement
public class Device {

    @XmlElement()
    private Map<Module, Integer> supportedModules = new EnumMap<>(Module.class);

    /**
     * Returns map of supported modules. The map key is module. The map value is version.
     *
     * @return map of supported modules.
     */
    public Map<Module, Integer> getSupportedModules() {
        return Collections.unmodifiableMap(supportedModules);
    }
}
