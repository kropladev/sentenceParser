package com.nordea.assignment.app.runner;

import com.nordea.assignment.app.facade.AppFacade;
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


    private AppFacade appFacade;

    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadAppRunner.class);

    public void runApplication() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                //First thread
                appFacade.putNewDataIntoBuffer(line);
                appFacade.retrieveAvailableSentecesFromBuffer();
                appFacade.putSentencesIntoMap();

                //Second thread
                appFacade.writeAvailableSentencesToFile();
            }
            appFacade.finalizeWriters();
        } catch (IOException e) {
            LOG.error("Exception while reading data. ", e);
        }
    }

    @Autowired
    @Qualifier("multiThreadAppFacade")
    public void setAppFacade(AppFacade appFacade) {
        this.appFacade = appFacade;
    }
}
