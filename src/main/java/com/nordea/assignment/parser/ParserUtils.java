package com.nordea.assignment.parser;

import com.nordea.assignment.model.Sentence;
import com.nordea.assignment.model.SentenceBuilder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kropla on 12.11.16.
 */
public class ParserUtils {
    public static List<String> retrieveWordsFromString(String stringSentence) {

        List<String> words =  new ArrayList<>();
        if(!stringSentence.isEmpty()) {
            for (String word : stringSentence.split("\\s+|[,-]")) {
                if(!word.isEmpty()) {
                    words.add(StringUtils.deleteAny(word, ",:;()"));
                }
            }
        }
        return words;
    }

    public static void sortWords(List<String> words) {
        Collections.sort(words, new WordComparator());
    }

    /**
     * # Don't match where we have a short word beginning with a capital (for titles)
     *      (?<!\b\p{Upper}\w{0,4})
     * # Only match when followed by a captial. (for abbreviations)
     *      (?=[.?!]\s*\p{Upper}) #
     * # match the punctuation:
     *      [.?!]
     *  \s* #also match white space, so no trimming is required (optional)

     * @param string
     * @return
     */
    public static String[] parseStringToSentencesArray(String string) {
        //return string.split("");
        return string.split("(?<!\\b\\p{Upper}\\w{0,4})(?=[.?!]\\s*\\p{Upper})([.?!]\\s*)|([.?!]\\s*)$");
    }


    public static Sentence retrieveNewSentenceFromString(String stringSentence) {
        List<String> words = retrieveWordsFromString(stringSentence);
        sortWords(words);
        return new SentenceBuilder().words(words).build();
    }
}
