package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;
import org.springframework.stereotype.Component;

/**
 * Created by kropla on 12.11.16.
 */
@Component("csvWriter")
public class CsvSentenceWriter extends SentenceFileWriter implements SentenceWriter {

    private static final String FILE_NAME = "out.csv";
    private static final String FIRST_COL_LABEL = "Sentence ";
    private static final String DELIMITER = ",";
    private static final String HEADER = "";
    private static final String FOOTER = "";
    private static int maxWordQtm = 0;
    private StringBuilder lineBuffer;
    private Sentence workSentence;

    CsvSentenceWriter() {
        super(FILE_NAME, HEADER, FOOTER);
    }


    @Override
    String formatLineSentence(Sentence sentence) {
        workSentence = sentence;
        buildFirstColumn();
        buildOtherColumns();
        checkMaxWordQtm();
        return lineBuffer.toString();
    }

    private void buildFirstColumn() {
        lineBuffer = new StringBuilder(FIRST_COL_LABEL)
                .append(prepareSentenceNo());
    }

    private int prepareSentenceNo() {
        return workSentence.getId() +1;
    }

    private void buildOtherColumns() {
        for(String word : workSentence.getWords()){
            lineBuffer.append(DELIMITER).append(word);
        }
    }

    private void checkMaxWordQtm() {
        if (maxWordQtm < workSentence.getWords().size()){
            maxWordQtm = workSentence.getWords().size();
        }
    }

    @Override
    public void finalizeFile(){
        super.finalizeFile();
        new CsvHeaderSentenceWriter(maxWordQtm,super.getFile()).appendHeader();
    }

}
