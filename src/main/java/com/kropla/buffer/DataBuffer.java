package com.kropla.buffer;

/**
 * Created by kropla on 11.11.16.
 */
public interface DataBuffer {

    /**
     * Add to buffer new String data taken from input.
     * @param data
     */
    void appendNewData(String data);

    /**
     * Retrieve buffer object
     * @return
     */
    StringBuffer getBufferData();

    /**
     * Remove whole sentence from buffer
     * @param length
     */
    void removeSentenceFromBuffer(int length);

    /**
     * check if dot is on index place
     * @param index
     * @return
     */
    boolean hasEndSymbolAtIndex(int index);
}
