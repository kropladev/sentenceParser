package com.nordea.assignment.app.facade;

import com.nordea.assignment.buffer.SentenceBufferHandler;
import com.nordea.assignment.model.Sentence;
import com.nordea.assignment.writer.SentenceWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by kropla on 12.11.16.
 */
@Component("singleThreadAppFacade")
public class SingleThreadAppFacade implements AppFacade{

    private SentenceBufferHandler bufferHandler;
    private SentenceWriter xmlWriter;
    private SentenceWriter csvWriter;

    private List<Sentence> availableSentences;
    private Map<Sentence,Integer> sentenceMap;

    @PostConstruct
    public void init(){
        sentenceMap = new TreeMap<>();
    }

    public void putNewDataIntoBuffer(String line) {
        bufferHandler.appendNewData(line);
    }

    public void retrieveAvailableSentecesFromBuffer() {
        availableSentences = bufferHandler.getAvailableSentencesFromBuffer();
    }

    public void putSentencesIntoMap() {

        for (Sentence sentence : availableSentences){
             sentenceMap.put(sentence,sentence.getId());
        }
    }

    public void writeAvailableSentencesToFile() {
        if(!sentenceMap.isEmpty()) {
            xmlWriter.writeSentences(sentenceMap);
            csvWriter.writeSentences(sentenceMap);
            sentenceMap.clear();
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
