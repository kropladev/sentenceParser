package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;

import java.util.Map;

/**
 * Created by kropla on 11.11.16.
 */
public interface SentenceWriter {

    /**
     * Writing sentences frm map to out stream
     * @param sentenceMap
     */
    void writeSentences(Map<Sentence,Integer> sentenceMap);

    /**
     * Finalize after writing to stream. Take lase action after write.
     */
    void finalizeWriter();
}
