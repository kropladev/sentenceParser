package com.kropla.app.stopwatch;

import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.util.StopWatch;

import static org.junit.Assert.*;

/**
 * Created by kropla on 15.11.16.
 */
public class StopWatcherTest {
    @Test
    public void shouldStartStopwatch() throws Exception {
        Stopwatch stopwach = new StopWatcher();
        stopwach.startStopwatch();
        StopWatch stopWatch = Whitebox.getInternalState(stopwach, "stopwatch");

        assertTrue(stopWatch.isRunning());
    }

    @Test
    public void logDurationTime() throws Exception {
        Stopwatch stopwach = new StopWatcher();
        stopwach.startStopwatch();
        stopwach.logDurationTime();
        assertTrue(true);
    }


    @Test
    public void logWarnMsg() throws Exception {
        Stopwatch stopwach = new StopWatcher();
        stopwach.logDurationTime();
        assertTrue(true);
    }

}