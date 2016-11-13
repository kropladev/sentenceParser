package com.nordea.assignment.parser;


import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.buffer.DataBufferImpl;
import com.nordea.assignment.model.Sentence;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by kropla on 12.11.16.
 */
public class ParserTest {

    @Test
    public void getSentencesFromEmptyBuffer(){
        Parser parser = new LineParser();
        DataBuffer buffer =  mock(DataBufferImpl.class);
        when(buffer.getData()).thenReturn(new StringBuffer("What	he  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese"));

        List<Sentence> sentences = parser.getAllSentencesFromBuffer(buffer);
    }



}
