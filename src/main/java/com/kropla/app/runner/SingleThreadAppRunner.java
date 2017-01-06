package com.kropla.app.runner;

import com.kropla.app.facade.AppFacade;
import com.kropla.app.stopwatch.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by kropla on 11.11.16.
 * Starts application in a single thread
 * While line is readed from file it is parsed and putted on a map with sentences
 * in buffer bean. After that all available (whole sentences that were ended with dot)
 * sentences are pushed from map to file writers
 *
 */
@Component("singleThreadAppRunner")
public class SingleThreadAppRunner implements AppRunnable {
    private static final Logger LOG = LoggerFactory.getLogger(SingleThreadAppRunner.class);
    private Stopwatch stopwatch;
    private AppFacade appFacade;

    public void runApplication() {
        LOG.info("Runing application in single thread mode.");
        stopwatch.startStopwatch();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                appFacade.putNewDataIntoBuffer(line);
                appFacade.writeAvailableSentences();
            }
            appFacade.finalizeWriters();
        } catch (IOException e) {
            LOG.error("Exception while reading data. ", e);
        }
        stopwatch.logDurationTime();
    }

    @Autowired
    @Qualifier("singleThreadAppFacade")
    public void setAppFacade(AppFacade appFacade) {
        this.appFacade = appFacade;
    }

    @Autowired
    public void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }
}
