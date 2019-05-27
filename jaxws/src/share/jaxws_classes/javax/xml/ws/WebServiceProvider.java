package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
/**
 * Used to annotate a Provider implementation class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceProvider {
    /**
     * Location of the WSDL description for the service.
     */
    String wsdlLocation() default "";

    /**
     * Service name.
     */
    String serviceName() default "";

    /**
     * Target namespace for the service
     */
    String targetNamespace() default "";

    /**
     * Port name.
     */
    String portName() default "";
}
