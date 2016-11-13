package com.nordea.assignment.parser;

import com.nordea.assignment.buffer.DataBuffer;
import com.nordea.assignment.model.Sentence;

import java.util.List;

/**
 * Created by kropla on 11.11.16.
 */
public interface Parser {
    List<Sentence> getAllSentencesFromBuffer(DataBuffer buffer);
}
