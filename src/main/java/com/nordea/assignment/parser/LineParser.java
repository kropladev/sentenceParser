package com.nordea.assignment.parser;

import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kropla on 11.11.16.
 */
@Component
public class LineParser implements Parser{

    @Override
    public List<Sentence> getAllSentencesFromBuffer(DataBuffer buffer) {
        List<Sentence> sentenceObjects = new ArrayList<>();

        String [] sentenceArray = ParserUtils.parseStringToSentencesArray( buffer.getData().toString());

        for (String stringSentence : sentenceArray ){
            if (buffer.hasEndSymbolAtIndex(stringSentence.length())) {
                sentenceObjects.add(ParserUtils.retrieveNewSentenceFromString(stringSentence));
                buffer.removeSentenceFromBuffer(stringSentence.length());
            }
        }

        return  sentenceObjects;
    }
}