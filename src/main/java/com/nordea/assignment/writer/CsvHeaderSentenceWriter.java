package com.nordea.assignment.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Created by kropla on 13.11.16.
 */
public class CsvHeaderSentenceWriter {
    private static final Logger LOG = LoggerFactory.getLogger(CsvHeaderSentenceWriter.class);
    private static final String WORD_SYMBOL = "Word ";
    private static final String TEMP_FILE_NAME = "tempFile";
    private File tempFile;
    private StringBuilder stringHeader;
    private int maximumWordQuantum;
    private File mainCsvFileName;

    public CsvHeaderSentenceWriter(int maxWordQtm, File csvFile) {
        this.mainCsvFileName = csvFile;
        this.maximumWordQuantum = maxWordQtm;
    }

    public void appendHeader() {
        try {
            generateHeaderSentence();
            writeHeaderSentenceIntoTempFile();
            mergeFiles();
        } catch (IOException e) {
            LOG.error("",e);
        }
    }

    private void generateHeaderSentence() {
        stringHeader = new StringBuilder();
        for (int i = 1; i <= maximumWordQuantum; i++){
            stringHeader.append(',').append(WORD_SYMBOL).append(i);
        }
    }

    private void writeHeaderSentenceIntoTempFile() {

        try {
            tempFile = new File(TEMP_FILE_NAME);
            PrintWriter writer = new PrintWriter(tempFile);
            writer.println(stringHeader.toString());
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void mergeFiles() throws IOException {
        Path outFile = mainCsvFileName.toPath();

        try(FileChannel out = FileChannel.open(outFile, CREATE, WRITE)) {

            Path inFile = tempFile.toPath();

            try(FileChannel in=FileChannel.open(inFile, READ)) {
                for(long p = 0, l = in.size(); p < l; )
                    p += in.transferTo(p, l-p, out);
            }
        }
    }
}
