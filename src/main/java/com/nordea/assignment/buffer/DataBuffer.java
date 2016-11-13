package com.nordea.assignment.buffer;

/**
 * Created by kropla on 11.11.16.
 */
public interface DataBuffer {

    void appendNewData(String data);

    StringBuffer getData();

    void removeSentenceFromBuffer(int length);

    boolean hasEndSymbolAtIndex(int index);
}
