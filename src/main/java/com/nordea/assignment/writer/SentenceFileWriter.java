package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kropla on 12.11.16.
 */
public abstract class SentenceFileWriter {
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
            e.printStackTrace();
        }
    }

    private void initFile() throws IOException {
        file = new File(fileName);
        writer = new PrintWriter(file);
        writeHeader();
    }

    public void finalizeFile(){
        finishFile();
        writer.flush();
        writer.close();
    }

    private void writeHeader(){
        writer.println(header);
    }

    void finishFile(){
        writer.println(footer);
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
