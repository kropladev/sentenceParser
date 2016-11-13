package com.nordea.assignment.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kropla on 12.11.16.
 */
public class ParserUtilsWordTest {
    private static final String MAIN_LINE = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者";
    private static final String COMMA_FIRST_LINE = "I could understand a word,perhaps because Chinese is my mother tongue";
    private static final String COMMA_SECOND_LINE = "I could understand a word, perhaps because Chinese is my mother tongue";
    private static final String THIRD_LINE = "I couldn't understand a word,perhaps because Chinese \n isn't my mother tongue";
    private static final String DASH = "Young marching around - he \nwas    furious";
    private static final String LONG = "Nordea Markets is the leading international capital markets operator and investment banking partner in the Nordic and Baltic Sea regions";
    private static final String BRACKET = "ours (and too)";

    private static final String EMPTY_LINE = "";
    private static final String WHITE_LINE = "          ";


    @Test
    public void retrieveWordsFromStringChineeseTest(){
        List<String> words = Arrays.asList("What","he", "shouted","was","shocking","停在那儿","你这肮脏的掠夺者");
        Assert.assertTrue(words.equals(ParserUtils.retrieveWordsFromString(MAIN_LINE)));
    }

    @Test
    public void retrieveWordsFromStringWithCommaTest(){
        List<String> words = Arrays.asList("I","could","understand","a","word","perhaps","because","Chinese","is","my","mother","tongue");

        List<String> testingWords = ParserUtils.retrieveWordsFromString(COMMA_FIRST_LINE);
        Assert.assertTrue(words.equals(testingWords));

        testingWords = ParserUtils.retrieveWordsFromString(COMMA_SECOND_LINE);
        Assert.assertTrue(words.equals(testingWords));

    }

    @Test
    public void retrieveWordsFromStringWithApostropheTest(){
        List<String> words = Arrays.asList("I","couldn't","understand","a","word","perhaps","because","Chinese","isn't","my","mother","tongue");

        List<String> testingWords = ParserUtils.retrieveWordsFromString(THIRD_LINE);
        Assert.assertTrue(words.equals(testingWords));

    }

    @Test
    public void retrieveWordsFromStringSortedTest(){
        List<String> words = Arrays.asList("he", "shocking","shouted","was","What","你这肮脏的掠夺者","停在那儿");
        List<String> wordsToTest = ParserUtils.retrieveWordsFromString(MAIN_LINE);
        ParserUtils.sortWords(wordsToTest);

        Assert.assertTrue(words.equals(wordsToTest));
    }

    @Test
    public void retrieveWordsFromStringWithDashInsideTest(){
        List<String> words = Arrays.asList("Young", "marching", "around" ,"he", "was","furious");
        List<String> wordsToTest = ParserUtils.retrieveWordsFromString(DASH);

        Assert.assertTrue(words.equals(wordsToTest));
    }

    @Test
    public void retrieveWordsFromEmptyStringTest(){
        List<String> words = new ArrayList<>();
        List<String> testingWords = ParserUtils.retrieveWordsFromString(EMPTY_LINE);

        Assert.assertTrue(words.equals(testingWords));
    }

    @Test
    public void retrieveWordsFromWhiteStringTest(){
        List<String> words = new ArrayList<>();
        List<String> testingWords = ParserUtils.retrieveWordsFromString(WHITE_LINE);

        Assert.assertTrue(words.equals(testingWords));
    }

    @Test
    public void retrieveWordsLongWholeLineTest(){
        List<String> words =  Arrays.asList("Nordea","Markets","is","the","leading","international","capital","markets","operator","and","investment","banking","partner","in","the","Nordic","and","Baltic","Sea","regions");
        List<String> testingWords = ParserUtils.retrieveWordsFromString(LONG);

        Assert.assertTrue(words.equals(testingWords));
    }

    @Test
    public void retrieveWordswithBracketLineTest(){
        List<String> words =  Arrays.asList("ours","and","too");
        List<String> testingWords = ParserUtils.retrieveWordsFromString(BRACKET);

        Assert.assertTrue(words.equals(testingWords));
    }

}
