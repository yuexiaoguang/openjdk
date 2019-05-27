package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 *  Used to annotate the <code>get<em>PortName</em>()</code>
 *  methods of a generated service interface.
 *
 *  <p>The information specified in this annotation is sufficient
 *  to uniquely identify a <code>wsdl:port</code> element
 *  inside a <code>wsdl:service</code>. The latter is
 *  determined based on the value of the <code>WebServiceClient</code>
 *  annotation on the generated service interface itself.
**/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebEndpoint {
  /**
   *  The local name of the endpoint.
  **/
  String name() default "";
}
