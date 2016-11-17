package com.nordea.assignment.buffer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by kropla on 15.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-beans.xml")
public class BufferHandlerTest {

    @Autowired
    BufferHandler bHand;

    @Before
    public void clearBufer(){
        bHand.clearSentenceBuffer();
    }

    @Test
    public void shouldInsertDataToBuffer(){
        bHand.appendNewData("This is test sentence.");
        Assert.assertTrue(bHand.bufferHasSentences());
    }


    @Test
    public void shouldBufferBeClearedAfterPushData(){
        bHand.appendNewData("This is test sentence.");
        Assert.assertTrue(bHand.bufferHasSentences());
        bHand.clearSentenceBuffer();
        Assert.assertTrue(!bHand.bufferHasSentences());
    }

    @Test
    public void shouldOneSentenceBeAvailable(){

        bHand.appendNewData("This is test sentence. Second sentence without dot");

        Map sentences = bHand.getSentencesFromBuffer();
        Assert.assertTrue(sentences.size() == 1);

    }

    @Test
    public void shouldNoneSentenceBeAvailable(){

        bHand.appendNewData("This is test sentence");
        Map sentences = bHand.getSentencesFromBuffer();
        Assert.assertTrue(sentences.size() == 0);

    }

    @Test
    public void shouldNoneSentenceBeAvailableWithEmptyInput(){

        bHand.appendNewData("");
        Map sentences = bHand.getSentencesFromBuffer();
        Assert.assertTrue(sentences.size() == 0);

    }
}
