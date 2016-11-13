package com.nordea.assignment.buffer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kropla on 12.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-beans.xml")
public class BufferTest {
    private static final String MAIN_LINE = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese ";
    private static final String FIRST_SENTENCE = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者";
    private static final String AFTER_REMOVE_LINE = "I couldn't understand a word,perhaps because Chinese ";
    private static final String ONE_LINE_DOT_END = "Nordea Markets is operator and the Nordic and Baltic Sea regions.";
    private static final String ONE_LINE_DOT_END_SENTENCE = "Nordea Markets is operator and the Nordic and Baltic Sea regions";

    @Autowired
    DataBuffer buffer;

    @Before
    public void flushBuffer(){
        StringBuffer buf = buffer.getData();
        buf.delete(0,  buf.length());
    }


    @Test
    public void appendDataTest(){
        buffer.appendNewData(MAIN_LINE);
        Assert.assertTrue(buffer.getData() != null);
    }

    @Test
    public void removeSingleSentenceFromBuffer(){
        buffer.appendNewData(MAIN_LINE);
        buffer.removeSentenceFromBuffer(FIRST_SENTENCE.length());
        Assert.assertTrue(AFTER_REMOVE_LINE.equals(buffer.getData().toString()));
    }

    @Test
    public void removeAllFromBuffer(){
        buffer.appendNewData(FIRST_SENTENCE);
        buffer.removeSentenceFromBuffer(FIRST_SENTENCE.length());
        buffer.getData().trimToSize();
        Assert.assertTrue(buffer.getData().length() == 0);
    }

    @Test
    public void checkEndSymbolAfterRemoveSentence(){
        buffer.appendNewData(MAIN_LINE);

        Assert.assertTrue(buffer.hasEndSymbolAtIndex(FIRST_SENTENCE.length()));

    }

    @Test
    public void checkEndSymbolAfterRemoveSentenceEndingWithDot(){
        buffer.appendNewData(ONE_LINE_DOT_END);

        Assert.assertTrue(buffer.hasEndSymbolAtIndex(ONE_LINE_DOT_END_SENTENCE.length()));

    }

    @Test
    public void checkIntegrityAfterRemoveSentence(){
        buffer.appendNewData(MAIN_LINE);
        buffer.hasEndSymbolAtIndex(FIRST_SENTENCE.length());
        buffer.removeSentenceFromBuffer(FIRST_SENTENCE.length());

        Assert.assertTrue(AFTER_REMOVE_LINE.equals(buffer.getData().toString()));

    }


}
