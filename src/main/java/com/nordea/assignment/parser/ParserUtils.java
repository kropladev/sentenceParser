package com.nordea.assignment.parser;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kropla on 12.11.16.
 */
public class ParserUtils {

    /**
     * # Don't match where we have a short word beginning with a capital (for titles)
     *      (?<!\b\p{Upper}\w{0,4})
     * # Only match when followed by a captial. (for abbreviations)
     *      (?=[.?!]\s*\p{Upper}) #
     * # match the punctuation:
     *      [.?!]
     *  \s* #also match white space, so no trimming is required (optional)

     * @param string String data representing the line data.
     * @return array of strings with whole sentences.
     */
    public static String[] parseStringToSentencesArray(String string) {
        return string.split("(?<!\\b\\p{Upper}\\w{0,4})(?=[.?!]\\s*\\p{Upper})([.?!]\\s*)|([.?!]\\s*)$");
    }

    /**
     * Get words List collection from stringSentence
     * @param stringSentence
     * @return sorted List of words String
     */
    public static List<String> retrieveSortedWordsFromString(String stringSentence) {

        List<String> words =  new ArrayList<>();
        if(!stringSentence.isEmpty()) {
            for (String word : stringSentence.split("\\s+|[,-]")) {
                if(!word.isEmpty()) {
                    words.add(StringUtils.deleteAny(word, ",:;()"));
                }
            }
        }
        sortWords(words);
        return words;
    }


    private static void sortWords(List<String> words) {
        Collections.sort(words, new WordComparator());
    }

}
