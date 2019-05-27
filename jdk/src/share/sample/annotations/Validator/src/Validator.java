/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import javax.xml.bind.ValidationException;
import java.util.function.Supplier;

/**
 * Enum of Validator implementations.
 */
public enum Validator {

    /**
     * This validator checks that the string represents an integer.
     */
    INTEGER_NUMBER {
                /**
                 * Checks that the string represents an integer.
                 *
                 * @param string - a string supplier
                 * @throws ValidationException if the validation check fails
                 */
                @Override
                void validate(Supplier<?> string) throws ValidationException {
                    try {
                        Integer.parseInt((String) string.get());
                    } catch (NumberFormatException ex) {
                        throw new ValidationException("Error while validating "
                                + string.get());
                    }
                }
            },
    /**
     * This validator checks that the string represents a positive number.
     */
    POSITIVE_NUMBER {
                /**
                 * Checks that the string represents a positive number.
                 *
                 * @param string - an string supplier
                 * @throws ValidationException if the validation check fails
                 */
                @Override
                void validate(Supplier<?> string) throws ValidationException {
                    try {
                        if (Double.compare(0.0, Double.parseDouble(
                                        (String) string.get())) > 0) {
                            throw new Exception();
                        }
                    } catch (Exception ex) {
                        throw new ValidationException("Error while validating "
                                + string.get());
                    }
                }
            };

    /**
     * Checks that the supplier is valid.
     *
     * @param string - a string supplier
     * @throws ValidationException if validation check fails
     */
    abstract void validate(Supplier<?> string) throws ValidationException;

}
