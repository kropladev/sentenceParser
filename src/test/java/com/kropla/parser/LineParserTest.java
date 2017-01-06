package com.kropla.parser;


import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by kropla on 12.11.16.
 */
public class LineParserTest {
    private static final String TEST_TEXT = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese ";
    private static final String TEST_TEXT2 = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者";

    @Test
    public void shouldGetSentencesArrayFromLineTest(){
        Parser parser = new LineParser();
        String[] expected = {"What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者","I couldn't understand a word,perhaps because Chinese "};

        String[] stringSentences = parser.parseLineToArrayOfStringSentences(TEST_TEXT);
        Assert.assertArrayEquals(expected, stringSentences);
    }

    @Test
    public void shouldGetWordsListFromSentenceString(){

    }

}
