/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.util.function.Supplier;

/**
 * Supplies a positive number.
 */
@Validate(value = Validator.INTEGER_NUMBER,
        description = "It's not an Integer ")
@Validate(value = Validator.POSITIVE_NUMBER,
        description = "It's not a positive Number")
public class PositiveIntegerSupplier implements Supplier<String> {

    /**
     * Returns a string representation of a positive integer.
     *
     * @return string representation of a positive integer.
     */
    @Override
    public String get() {
        return "20005"; //random number
    }
}
