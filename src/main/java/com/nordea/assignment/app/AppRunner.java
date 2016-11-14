package com.nordea.assignment.app;

import com.nordea.assignment.model.SentenceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class AppRunner {

    @Autowired
    SentenceHandler sentenceHandler;

    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    public void runApplication() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sentenceHandler.putNewDataIntoBuffer(line);
                sentenceHandler.getWholeAvailableSentecesFromBuffer();
                sentenceHandler.putSentencesIntoMap();
                sentenceHandler.writeAvailableSentencesToFile();
            }
            sentenceHandler.finalizeWriters();
        } catch (IOException e) {
            LOG.error("Exception while reading data. ", e);
        }
    }


}
