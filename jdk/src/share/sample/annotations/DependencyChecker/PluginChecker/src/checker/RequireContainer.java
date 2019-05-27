package checker;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A container for the repeatable @Require annotation.
 */
@Retention(RetentionPolicy.CLASS)
public @interface RequireContainer {

    Require[] value();
}
