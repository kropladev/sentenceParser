package com.nordea.assignment.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by kropla on 13.11.16.
 * Building header for CSV file based on maximum words quantum from sentences.
 * Implemented solution concern on creating new header file and after that
 * this file is merged woth created before csv file with words in sentences data.
 */
public class CsvHeaderSentenceWriter {
    private static final Logger LOG = LoggerFactory.getLogger(CsvHeaderSentenceWriter.class);
    private static final String WORD_SYMBOL = "Word ";
    private static final String TEMP_FILE_NAME = "tempFile";
    private static final String TEMP_FILE_NAME_SECOND = "tempToRemove";

    private File tempFile;
    private StringBuilder stringHeader;
    private int maximumWordQuantum;
    private File mainCsvFile;

    public CsvHeaderSentenceWriter(int maxWordQtm, File csvFile) {
        this.mainCsvFile = csvFile;
        this.maximumWordQuantum = maxWordQtm;
    }

    public void appendHeader() {
        try {
            generateHeaderSentence();
            writeHeaderSentenceIntoTempFile();
            mergeFiles();
            renameFiles();
        } catch (IOException e) {
            LOG.error("Exception during csv header writing. ",e);
        }
    }

    /**
     * Create first row in csv - header based on maximum word quantum
     */
    private void generateHeaderSentence() {
        stringHeader = new StringBuilder();
        for (int i = 1; i <= maximumWordQuantum; i++){
            stringHeader.append(',').append(WORD_SYMBOL).append(i);
        }
    }

    /**
     * Write prepared header into temporary file
     * @throws FileNotFoundException
     */
    private void writeHeaderSentenceIntoTempFile() throws FileNotFoundException {

            tempFile = new File(TEMP_FILE_NAME);
            PrintWriter writer = new PrintWriter(tempFile);
            writer.println(stringHeader.toString());
            writer.flush();
            writer.close();
    }

    /**
     * Merge temporary file (with header data) with csv data file created before by other writer
     * @throws IOException
     */
    private void mergeFiles() throws IOException {
        try {
            //init
            FileChannel srcChannel = getRandomAccessFile(mainCsvFile, "r");
            FileChannel destChannel = getRandomAccessFile(tempFile, "rw");

            //transfer
            destChannel.position(destChannel.size());
            srcChannel.transferTo(0, srcChannel.size(), destChannel);

            //close
            srcChannel.close();
            destChannel.close();

        } catch (FileNotFoundException fe){
            LOG.error("Files for merge not found.", fe);
        }

    }

    private FileChannel getRandomAccessFile(File tempFile, String accessRights) throws FileNotFoundException {
        return new RandomAccessFile(tempFile, accessRights).getChannel();
    }


    private void renameFiles() throws IOException{
        String oldCsvName = mainCsvFile.getName();

        Files.deleteIfExists(new File(TEMP_FILE_NAME_SECOND).toPath());
        Files.deleteIfExists(mainCsvFile.toPath());

        renameFile(tempFile.toPath(), oldCsvName);
    }

    private void renameFile(Path fileSource, String newName) throws IOException{
        Files.move(fileSource, fileSource.resolveSibling(newName));
    }
}
