package com.kropla.writer;

import com.kropla.model.Sentence;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by kropla on 14.11.16.
 */
public class CsvSentenceWriterTest {
    @Test
    public void shouldFormatLinesInProperWay(){
        //G
        Sentence sentence = new Sentence(11, Arrays.asList("a","better","way"));
        SentenceFileWriter writer = new CsvSentenceWriter();
        String expected = "Sentence 12,a,better,way";

        //W
        String line = writer.formatLineSentence(sentence);

        //T
        assertEquals(expected, line);

    }
}