package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kropla on 14.11.16.
 */
@RunWith(JUnit4.class)
public class XmlSentenceWriterTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldFormatSentenceToXmlNode(){
        //G
        Sentence sentence = new Sentence(11, Arrays.asList("a","better","way"));
        SentenceFileWriter writer = new XmlSentenceWriter();
        String expected = "<sentence><word>a</word><word>better</word><word>way</word></sentence>";

        //W
        String line = writer.formatLineSentence(sentence);

        //T
        assertEquals(expected, line);

    }

    @Test
    public void shouldWriteSentenceToFile(){
        Sentence sentence = new Sentence(11, Arrays.asList("a","better","way"));
        SentenceFileWriter writer = new XmlSentenceWriter();

        Map<Sentence,Integer> map = new HashMap<>();
        map.put(sentence,1);

        writer.writeSentences(map);

        File file = new File(XmlSentenceWriter.FILE_NAME);

        assertTrue(file.exists());
    }

}