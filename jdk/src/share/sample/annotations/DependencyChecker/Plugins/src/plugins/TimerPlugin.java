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
 * Timer plug-in is used to support an alarm and a timer. It depends on Display and
 * Clock modules.
 */
@Require(Module.DISPLAY)
@Require(value = Module.CLOCK, maxVersion = 3)
public class TimerPlugin {

    /**
     * Sets timer.
     *
     * @param time - the remaining time.
     */
    public void timer(long time) {
        //start timer
        //show the remaining time on display
    }

    /**
     * Sets alarm.
     *
     * @param time - the alarm time.
     */
    public void alarm(long time) {
        //start alarm
        //show current time and alarm time on display
    }
}
