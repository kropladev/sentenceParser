package com.nordea.assignment.buffer;

import com.nordea.assignment.model.Sentence;
import com.nordea.assignment.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kropla on 15.11.16.
 */
@Component
public class SentenceBufferHandler {

    @Autowired
    DataBuffer buffer;

    @Autowired
    Parser parser;

    private int sentenceCounter;

    public void appendNewData(String line) {
        buffer.appendNewData(line);
    }

    public List<Sentence> getAvailableSentencesFromBuffer(){
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
}
