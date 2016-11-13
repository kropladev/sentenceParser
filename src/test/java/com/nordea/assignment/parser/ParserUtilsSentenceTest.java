package com.nordea.assignment.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kropla on 12.11.16.
 */
public class ParserUtilsSentenceTest {
    private static final String MAIN_LINE = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese ";
    private static final String ABBREV_LINE = " isn't my mother tongue. I was just standing there watching Mr. Young marching around - he ";
    private static final String LINE_WITH_DOT_END = "Nordea Markets is the leading international capital markets.";

    @Test
    public void retrieveSentenceFromStringTest(){

        List<String> sentences = Arrays.asList("What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者","I couldn't understand a word,perhaps because Chinese ");

        String[] sentencesToTest = ParserUtils.parseStringToSentencesArray(MAIN_LINE);

        Assert.assertTrue(sentences.equals(Arrays.asList(sentencesToTest)));
    }

    @Test
    public void retrieveSentenceFromStringWithAbbreviationTest(){

        List<String> sentences = Arrays.asList(" isn't my mother tongue","I was just standing there watching Mr. Young marching around - he ");

        String[] sentencesToTest = ParserUtils.parseStringToSentencesArray(ABBREV_LINE);

        Assert.assertTrue(sentences.equals(Arrays.asList(sentencesToTest)));
    }

    @Test
    public void retrieveSentenceFromStringEndingWithDotTest(){

        List<String> sentences = Arrays.asList("Nordea Markets is the leading international capital markets");

        String[] sentencesToTest = ParserUtils.parseStringToSentencesArray(LINE_WITH_DOT_END);

        Assert.assertTrue(sentences.equals(Arrays.asList(sentencesToTest)));
    }
}
