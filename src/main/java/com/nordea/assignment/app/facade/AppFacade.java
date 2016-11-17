package com.nordea.assignment.app.facade;

/**
 * Created by kropla on 16.11.16.
 */
public interface AppFacade {
    /**
     * Put new string line to buffer
     * @param line
     */
    void putNewDataIntoBuffer(String line);

    /**
     * If there ate exists sentences write them using writers
     */
    void writeAvailableSentences();

    /**
     * Do finish actions on writers
     */
    void finalizeWriters();
}
