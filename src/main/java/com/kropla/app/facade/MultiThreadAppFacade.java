package com.kropla.app.facade;

import com.kropla.buffer.BufferHandler;
import com.kropla.writer.SentenceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by kropla on 12.11.16.
 */
@Component("multiThreadAppFacade")
public class MultiThreadAppFacade implements AppFacade{
    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadAppFacade.class);

    private BufferHandler bufferHandler;
    private SentenceWriter xmlWriter;
    private SentenceWriter csvWriter;

    private static volatile boolean stop = false;

    public void putNewDataIntoBuffer(String line) {
        synchronized (bufferHandler) {
            while (bufferHandler.bufferHasSentences()) {
                try {
                    bufferHandler.wait();
                } catch (InterruptedException iex) {
                    LOG.error("Exception while put data. ", iex);
                }
            }

            if (line != "STOP"){
                stop = true;
                bufferHandler.appendNewData(line);
            }
            bufferHandler.notifyAll();
        }
    }

    public  void writeAvailableSentences() {
        synchronized (bufferHandler) {
            while (!bufferHandler.bufferHasSentences() && !stop) {
                try {
                    bufferHandler.wait();
                } catch (InterruptedException iex) {
                    LOG.error("Exception while writing data. ", iex);
                }
            }

            xmlWriter.writeSentences(bufferHandler.getSentencesFromBuffer());
            csvWriter.writeSentences(bufferHandler.getSentencesFromBuffer());
            bufferHandler.clearSentenceBuffer();
            bufferHandler.notifyAll();
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
