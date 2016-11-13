package com.nordea.assignment.app;

import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.model.SentenceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 * Created by kropla on 11.11.16.
 */
@Component
public class AppRunner {

    @Autowired
    SentenceHandler sentenceHandler;

    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    public void runApplication(String[] args) {
        BufferedReader reader = null;

        if (args != null && args.length > 0){
            try {
                reader = new BufferedReader(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
                LOG.error("Exception while reading file. ", e);
            }
        } else {

            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sentenceHandler.putNewDataIntoBuffer(line);
                sentenceHandler.getWholeAvailableSentecesFromBuffer();
                sentenceHandler.putSentencesIntoMap();
                sentenceHandler.writeAvailableSentencesToFile();
            }
            sentenceHandler.closeFile();
        } catch (IOException e) {
            LOG.error("Exception while reading data. ", e);
        }
    }

    public void runApplication(){
        runApplication(null);
    }
}
