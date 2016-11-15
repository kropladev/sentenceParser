package com.nordea.assignment.buffer;

/**
 * Created by kropla on 11.11.16.
 */
public interface DataBuffer {

    /**
     * Add to buffer new String data taken from input.
     * @param data
     */
    void appendNewData(String data);

    StringBuffer getBufferData();

    void removeSentenceFromBuffer(int length);

    boolean hasEndSymbolAtIndex(int index);
}
