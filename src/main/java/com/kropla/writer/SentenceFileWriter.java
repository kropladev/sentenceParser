package com.kropla.writer;

import com.kropla.model.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kropla on 12.11.16.
 */
public abstract class SentenceFileWriter {
    private static final Logger LOG = LoggerFactory.getLogger(SentenceFileWriter.class);

    private File file;
    private String fileName;
    private String header;
    private String footer;
    private PrintWriter writer;

    SentenceFileWriter(String fName, String headerString, String footerString){
        this.fileName = fName;
        this.header = headerString;
        this.footer = footerString;
        try {
            initFile();
        } catch (IOException e) {
            LOG.error("Exception while initializing file.", e);
        }
    }

    private void initFile() throws IOException {
        file = new File(fileName);
        writer = new PrintWriter(file);
        writeHeader();
    }

    private void writeHeader(){
        if (!header.isEmpty()) {
            writer.println(header);
        }
    }

    public void finalizeWriter(){
        finishFile();
        writer.flush();
        writer.close();
    }

    void finishFile(){
        if (!footer.isEmpty()) {
            writer.println(footer);
        }
    }

    abstract String formatLineSentence(Sentence sentence);

    public void writeSentences(Map<Sentence,Integer> sentenceMap) {
        for(Iterator<Map.Entry<Sentence, Integer>> it = sentenceMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Sentence, Integer> entry = it.next();
            Sentence sentence = entry.getKey();
            writeToFile(formatLineSentence(sentence));
        }
    }

    private void writeToFile(String line){
        writer.println(line);
    }

    File getFile() {
        return file;
    }
}
