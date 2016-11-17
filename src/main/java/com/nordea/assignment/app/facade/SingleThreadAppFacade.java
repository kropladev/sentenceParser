package com.nordea.assignment.app.facade;

import com.nordea.assignment.buffer.BufferHandler;
import com.nordea.assignment.writer.SentenceWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by kropla on 12.11.16.
 */
@Component("singleThreadAppFacade")
public class SingleThreadAppFacade implements AppFacade{

    private BufferHandler bufferHandler;
    private SentenceWriter xmlWriter;
    private SentenceWriter csvWriter;

    public void putNewDataIntoBuffer(String line) {
        bufferHandler.appendNewData(line);
    }

    public void writeAvailableSentences() {
        if(bufferHandler.bufferHasSentences()) {
            xmlWriter.writeSentences(bufferHandler.getSentencesFromBuffer());
            csvWriter.writeSentences(bufferHandler.getSentencesFromBuffer());
            bufferHandler.clearSentenceBuffer();
        }
    }

    public void finalizeWriters() {
        xmlWriter.finalizeWriter();
        csvWriter.finalizeWriter();
    }

    @Autowired
    public void setBufferHandler(BufferHandler bufferHandler) {
        this.bufferHandler = bufferHandler;
    }

    @Autowired
    @Qualifier("xmlWriter")
    public void setXmlWriter(SentenceWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }

    @Qualifier("csvWriter")
    @Autowired
    public void setCsvWriter(SentenceWriter csvWriter) {
        this.csvWriter = csvWriter;
    }

}
