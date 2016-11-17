package com.nordea.assignment.buffer;

import com.nordea.assignment.model.Sentence;

import java.util.Map;

/**
 * Created by kropla on 17.11.16.
 */
public interface BufferHandler {

    /**
     * Put new string data into buffer
     * @param line
     */
    void appendNewData(String line);

    /**
     * check if buffer has available sentences/
     * @return
     */
    boolean bufferHasSentences();

    /**
     * Get map with sentences from buffer
     * @return
     */
    Map<Sentence,Integer> getSentencesFromBuffer();

    /**
     * Clear sentence buffer
     */
    void clearSentenceBuffer();
}
