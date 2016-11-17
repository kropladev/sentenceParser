package com.nordea.assignment.app.stopwatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by kropla on 16.11.16.
 */
@Component
public class StopWatcher implements Stopwatch {
    private static final Logger LOG = LoggerFactory.getLogger(StopWatcher.class);
    private StopWatch stopwatch;

    @Override
    public void startStopwatch() {
        stopwatch = new StopWatch();
        stopwatch.start();
    }

    @Override
    public void logDurationTime() {
        try {
            stopwatch.stop();
            LOG.info("Total application duration time[s]: " + stopwatch.getTotalTimeSeconds());
        } catch (IllegalStateException e){
            LOG.warn("Stopwatch wasn't started. There is no information about time duration.");
        }
    }
}
