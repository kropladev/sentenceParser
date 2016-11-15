package com.nordea.assignment.parser;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kropla on 11.11.16.
 */
@Component
public class LineParser implements Parser{

    @Override
    public String [] parseLineToArrayOfStringSentences(String lineData){
        return ParserUtils.parseStringToSentencesArray(lineData);
    }

    @Override
    public List<String> parseSentenceStringToWords(String stringSentence) {
        return ParserUtils.retrieveSortedWordsFromString(stringSentence);
    }

}