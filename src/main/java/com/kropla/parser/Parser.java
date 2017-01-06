package com.kropla.parser;

import java.util.List;

/**
 * Created by kropla on 11.11.16.
 */
public interface Parser {

    /**
     * Parse string line to available whole sentences ending with dot.
     * @param lineData String data from single buffer line
     * @return array of string with whole sentences.
     */
    String[] parseLineToArrayOfStringSentences(String lineData);

    /**
     * Parse single (whole) sentence from String type to List of String words.
     * @param stringSentence String with whole sentence.
     * @return List with words.
     */
    List<String> parseSentenceStringToWords(String stringSentence);
}
