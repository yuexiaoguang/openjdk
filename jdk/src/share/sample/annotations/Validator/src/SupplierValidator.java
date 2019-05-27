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
 * Validates the supplier.
 */
public class SupplierValidator {

    /**
     * Validates the supplier.
     *
     * @param supplier - Supplier that needs to be validated.
     * @return true if supplier has passed validation check. False otherwise.
     */
    public static boolean validate(Supplier<?> supplier) {
        for (Validate annotation
                : supplier.getClass().getAnnotationsByType(Validate.class)) {
            try {
                annotation.value().validate(supplier);
            } catch (ValidationException e) {
                System.out.println(annotation.description());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
