package com.nordea.assignment.app.facade;

import com.nordea.assignment.buffer.SentenceBufferHandler;
import com.nordea.assignment.model.Sentence;
import com.nordea.assignment.writer.SentenceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by kropla on 12.11.16.
 */
@Component("multiThreadAppFacade")
public class MultiThreadAppFacade implements AppFacade{
    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadAppFacade.class);

    private SentenceBufferHandler bufferHandler;
    private SentenceWriter xmlWriter;
    private SentenceWriter csvWriter;

    private List<Sentence> availableSentences;
    private Map<Sentence,Integer> sentenceMap;

    private static volatile boolean stop = false;
    @PostConstruct
    public void init(){
        sentenceMap = new TreeMap<>();
    }

    public void putNewDataIntoBuffer(String line) {
        synchronized (sentenceMap) {
            while (dataIsAvailable()) {
                try {
                    sentenceMap.wait();
                } catch (InterruptedException iex) {
                    LOG.error("Exception while put data. ", iex);
                }
            }

            if (line != "STOP"){
                stop = true;
                bufferHandler.appendNewData(line);
                retrieveAvailableSentecesFromBuffer();
                putSentencesIntoMap();
            }
                sentenceMap.notifyAll();

        }
    }

    private synchronized boolean dataIsAvailable() {
        return !sentenceMap.isEmpty();
    }

    public void retrieveAvailableSentecesFromBuffer() {
        availableSentences = bufferHandler.getAvailableSentencesFromBuffer();
    }


    public void putSentencesIntoMap() {
        for (Sentence sentence : availableSentences){
             sentenceMap.put(sentence,sentence.getId());
        }
    }

    public  void writeAvailableSentencesToFile() {
        synchronized (sentenceMap) {
            while (!dataIsAvailable() && !stop) {
                try {
                    sentenceMap.wait();
                } catch (InterruptedException iex) {
                    LOG.error("Exception while writing data. ", iex);
                }
            }

            xmlWriter.writeSentences(sentenceMap);
            csvWriter.writeSentences(sentenceMap);
            sentenceMap.clear();
            sentenceMap.notifyAll();
        }
    }

    public void finalizeWriters() {
        xmlWriter.finalizeWriter();
        csvWriter.finalizeWriter();
    }


    @Autowired
    public void setBufferHandler(SentenceBufferHandler bufferHandler) {
        this.bufferHandler = bufferHandler;
    }

    @Autowired
    @Qualifier("xmlWriter")
    public void setXmlWriter(SentenceWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }


    @Qualifier("csvWriter")
    @Autowired
    public void setCsvWriter(SentenceWriter csvWriter) {
        this.csvWriter = csvWriter;
    }
}
