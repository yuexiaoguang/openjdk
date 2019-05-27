/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the class should be validated by the specified validator.
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidateContainer.class)
public @interface Validate {

    /**
     * Returns the validator that should validate the annotated class.
     *
     * @return Validator that should validate annotated class.
     */
    Validator value();

    /**
     * Returns text to describe the failure of the validation check.
     *
     * @return text to describe the failure of the validation check.
     */
    String description() default "";
}

/**
 * A container for the repeatable @Validate annotation.
 *
 * @author Andrey Nazarov
 */
@Retention(RetentionPolicy.RUNTIME)
@interface ValidateContainer {

    Validate[] value();
}
