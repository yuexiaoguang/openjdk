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

/**
 * BoilerPlugin provides support for boiling water and keeping water warm.
 */
@Require(value = Module.CLOCK, maxVersion = 3)
@Require(value = Module.THERMOMETER)
@Require(value = Module.HEATER)
@Require(value = Module.LED, optional = true) //will use if present
public class BoilerPlugin {

    /**
     * Heats water up to 100 degrees Celsius.
     */
    public void boil() {
        boil(100);
    }

    /**
     * Heats water up to temperature.
     *
     * @param temperature - desired temperature of the water in the boiler
     */
    public void boil(int temperature) {
        /*
         * Turn on heater and wait while temperature reaches desired temperature
         * in Celsius. Finally, turn off heater.
         * If present, the LED light changes color according to the temperature.
         */
    }

    /**
     * Keeps desired temperature.
     *
     * @param temperature - desired temperature of the water in the boiler
     * @param seconds - period of time for checking temperature in seconds
     */
    public void keepWarm(int temperature, int seconds) {
        //Every n seconds check temperature and warm up, if necessary.
    }

}
