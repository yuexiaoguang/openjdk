package com.sun.org.apache.xerces.internal.xs.datatypes;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Interface to expose the values for all date-time related types. The following
 * table shows the methods defined for various XML Schema 1.0 built-in types. 'X'
 * marks whether a particular method is defined for a particular type. Accessing undefined
 * methods may return unexpected values.
 *
 * <table border="1">
 * <br/>
 * <tr>
 * <td> XML Schema Datatype </td>
 * <td> getYears() </td>
 * <td> getMonths() </td>
 * <td> getDays() </td>
 * <td> getHours() </td>
 * <td> getMinutes() </td>
 * <td> getSeconds() </td>
 * <td> getTimeZoneHours() </td>
 * <td> getTimeZoneMinutes() </td>
 * <td> getXMLGregorianCalendar() </td>
 * <td> getDuration() </td>
 * <td> hasTimeZone() </td>
 * <td> normalize() </td>
 * <td> isNormalized() </td>
 * <td> getLexicalValue() </td>
 * </tr>
 * <tr>
 * <td> gYear </td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonth </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gDay </td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gYearMonth </td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonthDay </td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> date </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> time </td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> datetime </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> duration </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * </tr>
 * </table>
 * </p>
 *
 * @author Ankit Pasricha, IBM
 *
 */
public interface XSDateTime {

    /**
     * @return years - can be negative for date-time related types;
     *
     */
    public int getYears();

    /**
     * @return months - can be negative only for duration types;
     *                  For duration types, it returns years*12 + months
     */
    public int getMonths();

    /**
     * @return days - cannot be negative;
     *
     */
    public int getDays();

    /**
     * @return hours - cannot be negative;
     *
     */
    public int getHours();

    /**
     * @return minutes - cannot be negative;
     *
     */
    public int getMinutes();

    /**
     * @return seconds - can be negative only for durations;
     *                   For duration types, it returns days*24*3600 + hours*3600
     *                                                  + minutes*60 + seconds
     */
    public double getSeconds();

    /**
     * @return boolean (true when timezone is specified in the original lexical value)
     *
     */
    public boolean hasTimeZone();

    /**
     * @return timezone hours (for GMT-xx:xx this will be negative),
     *
     */
    public int getTimeZoneHours();

    /**
     * @return timezone minutes (for GMT-xx:xx this will be negative),
     *
     */
    public int getTimeZoneMinutes();

    /**
     * @return the original lexical value
     */
    public String getLexicalValue();

    /**
     * @return a new date-time related object with normalized values
     *         (has no effect on objects already
     *          normalized)
     */
    public XSDateTime normalize();

    /**
     * @return whether a date-time related object is normalized or not
     *         (value is not useful for types where timezone is not specified)
     */
    public boolean isNormalized();

    /**
     * @return an un-normalized XMLGregorianCalendar (if applicable otherwise null)
     */
    public XMLGregorianCalendar getXMLGregorianCalendar();

    /**
     * @return a Duration (if applicable otherwise null)
     */
    public Duration getDuration();
}
