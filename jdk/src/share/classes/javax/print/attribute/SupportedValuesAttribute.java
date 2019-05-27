package javax.print.attribute;

/**
 * Interface SupportedValuesAttribute is a tagging interface which a printing
 * attribute class implements to indicate the attribute describes the supported
 * values for another attribute. For example, if a Print Service instance
 * supports the {@link javax.print.attribute.standard.Copies Copies}
 * attribute, the Print Service instance will have a {@link
 * javax.print.attribute.standard.CopiesSupported CopiesSupported} attribute,
 * which is a SupportedValuesAttribute giving the legal values a client may
 * specify for the {@link javax.print.attribute.standard.Copies Copies}
 * attribute.
 * <P>
 */
public interface SupportedValuesAttribute extends Attribute {
}
