package com.nordea.assignment.buffer;

import com.nordea.assignment.model.Sentence;
import com.nordea.assignment.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by kropla on 15.11.16.
 */
@Component
public class SentenceBufferHandler implements BufferHandler{
    private List<Sentence> availableSentences;
    //better fits Queue - but Map Interface was in requirements
    private Map<Sentence,Integer> sentenceMap;
    private int sentenceCounter;
    private DataBuffer buffer;
    private Parser parser;

    @PostConstruct
    public void init(){
        sentenceMap = new TreeMap<>();
    }

    public void appendNewData(String line) {
        buffer.appendNewData(line);
        retrieveAvailableSentecesFromBuffer();
    }

    private void retrieveAvailableSentecesFromBuffer() {
        availableSentences = getAvailableSentencesFromBuffer();
        putSentencesIntoCollection();
    }

    private void putSentencesIntoCollection() {
        for (Sentence sentence : availableSentences){
            sentenceMap.put(sentence,sentence.getId());
        }
    }

    private List<Sentence> getAvailableSentencesFromBuffer(){
        String [] sentenceArray = retrieveArrayOfStringSentences();
        return retrieveListOfSentences(sentenceArray);
    }

    private String[] retrieveArrayOfStringSentences() {
        String lineData = buffer.getBufferData().toString();
        return parser.parseLineToArrayOfStringSentences(lineData);
    }

    private List<Sentence> retrieveListOfSentences(String[] sentenceArray) {
        List<Sentence> sentenceObjects = new ArrayList<>();
        for (String stringSentence : sentenceArray ){
            if (buffer.hasEndSymbolAtIndex(stringSentence.length())) {
                List<String> words = parser.parseSentenceStringToWords(stringSentence);
                sentenceObjects.add(new Sentence(sentenceCounter++,words));
                buffer.removeSentenceFromBuffer(stringSentence.length());
            }
        }
        return sentenceObjects;
    }

    public boolean bufferHasSentences(){
        return !sentenceMap.isEmpty();
    }

    public Map<Sentence,Integer> getSentencesFromBuffer() {
        return sentenceMap;
    }

    public void clearSentenceBuffer() {
        sentenceMap.clear();
    }

    @Autowired
    public void setBuffer(DataBuffer buffer) {
        this.buffer = buffer;
    }

    @Autowired
    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
