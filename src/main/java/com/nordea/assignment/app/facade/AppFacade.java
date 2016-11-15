package com.nordea.assignment.app.facade;

/**
 * Created by kropla on 16.11.16.
 */
public interface AppFacade {
    void putNewDataIntoBuffer(String line);
    void retrieveAvailableSentecesFromBuffer();
    void putSentencesIntoMap();//TODO to be remove
    void writeAvailableSentencesToFile();
    void finalizeWriters();
}
