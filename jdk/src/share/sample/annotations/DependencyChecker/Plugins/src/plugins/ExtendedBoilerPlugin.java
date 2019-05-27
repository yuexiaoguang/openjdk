/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package plugins;

import checker.Module;
import checker.Require;
import java.util.Calendar;

/**
 * Introduces new features for BoilerPlugin. Features are boiling water by an
 * SMS and boiling water by date with notification by a phone call.
 */
@Require(value = Module.SPEAKER)
@Require(value = Module.GSM, minVersion = 3)
@Require(value = Module.DISPLAY)
public class ExtendedBoilerPlugin extends BoilerPlugin {

    /**
     * Boils water at the appointed time and wakes you up by a ring and phone
     * call. Shows "Good morning" and a quote of the day from the Internet on the
     * display.
     *
     * @param calendar - date and time when water should be boiled
     * @param phoneNumber - phone number to call
     */
    public void boilAndWakeUp(Calendar calendar, int phoneNumber) {
        //implementation
    }

    /**
     * Boils water at the appointed time by getting an SMS of fixed format.
     * Sends an SMS on finish.
     *
     * @param sms - text of SMS
     */
    public void boilBySMS(String sms) {
        //implementation
    }
}
