package com.nordea.assignment.parser;


import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.buffer.DataBufferImpl;
import com.nordea.assignment.model.Sentence;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
