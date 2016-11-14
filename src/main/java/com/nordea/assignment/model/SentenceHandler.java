package com.nordea.assignment.model;

import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.parser.Parser;
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
@Component
public class SentenceHandler {

    @Autowired
    DataBuffer buffer;

    @Autowired
    Parser parser;

    @Autowired
    @Qualifier("xmlWriter")
    private SentenceWriter xmlWriter;

    @Qualifier("csvWriter")
    @Autowired
    private SentenceWriter csvWriter;

    private List<Sentence> availableSentences;
    private Map<Sentence,Integer> sentenceMap;

    @PostConstruct
    public void init(){
        sentenceMap = new TreeMap<>();
    }

    public void putNewDataIntoBuffer(String line) {
        buffer.appendNewData(line);
    }

    public void getWholeAvailableSentecesFromBuffer() {
        availableSentences = parser.getAllSentencesFromBuffer(buffer);
    }

    public void putSentencesIntoMap() {

        for (Sentence sentence : availableSentences){
             sentenceMap.put(sentence,sentence.getId());
        }
    }

    public void finalizeWriters() {
        xmlWriter.finalizeFile();
        csvWriter.finalizeFile();
    }

    public void writeAvailableSentencesToFile() {
        if(!sentenceMap.isEmpty()) {
            xmlWriter.writeSentences(sentenceMap);
            csvWriter.writeSentences(sentenceMap);
            sentenceMap.clear();
        }
    }


}
