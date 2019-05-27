package com.sun.org.glassfish.external.probe.provider.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProbeProvider {

    public String providerName() default "";
    public String moduleProviderName() default "";
    public String moduleName() default "";
    public String probeProviderName() default "";

}
