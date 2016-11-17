package com.nordea.assignment.app.runner;

import com.nordea.assignment.app.facade.AppFacade;
import com.nordea.assignment.app.runner.stopwatch.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kropla on 11.11.16.
 * Starts application in a single thread
 * While line is readed from file it is parsed and putted on a map with sentences
 * in buffer bean. After that all available (whole sentences that were ended with dot)
 * sentences are pushed from map to file writers
 *
 */
@Component("multiThreadAppRunner")
public class MultiThreadAppRunner implements AppRunnable {
    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadAppRunner.class);
    private Stopwatch stopwatch;
    private AppFacade appFacade;
    private static boolean stopRequested;
    private static synchronized void requestStop() {
        stopRequested = true;
    }
    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public void runApplication() {
        LOG.info("Runing application in two threads mode.");
        stopwatch.startStopwatch();
        new Producer().start();
        new Consumer().start();
    }

    @Autowired
    @Qualifier("multiThreadAppFacade")
    public void setAppFacade(AppFacade appFacade) {
        this.appFacade = appFacade;
    }

    class Producer extends Thread{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        public void run(){
            try {
                while ((line = reader.readLine()) != null) {
                    appFacade.putNewDataIntoBuffer(line);
                }
                requestStop();
                appFacade.putNewDataIntoBuffer("STOP");
            } catch (IOException e) {
                LOG.error("Exception while reading data. ", e);
            }
        }
    }

    class Consumer extends Thread{
        public void run() {
            while (!stopRequested()) {
                appFacade.writeAvailableSentences();
            }
            appFacade.finalizeWriters();
            stopwatch.logDurationTime();
        }
    }

    @Autowired
    public void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }
}
