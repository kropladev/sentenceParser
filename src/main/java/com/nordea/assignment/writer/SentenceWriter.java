package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;

import java.util.Map;

/**
 * Created by kropla on 11.11.16.
 */
public interface SentenceWriter {

    void writeSentences(Map<Sentence,Integer> sentenceMap);

    void closeFile();
}
