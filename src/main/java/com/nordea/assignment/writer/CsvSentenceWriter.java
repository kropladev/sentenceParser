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


    CsvSentenceWriter() {
        super(FILE_NAME, HEADER, FOOTER);
    }


    @Override
    String formatLineSentence(Sentence sentence) {
        StringBuilder line = new StringBuilder(FIRST_COL_LABEL)
                .append(sentence.getId());
        for(String word : sentence.getWords()){
            line.append(DELIMITER).append(word);
        }
        return line.toString();
    }
}
