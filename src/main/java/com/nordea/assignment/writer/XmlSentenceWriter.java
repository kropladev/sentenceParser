package com.nordea.assignment.writer;

import com.nordea.assignment.model.Sentence;
import org.springframework.stereotype.Component;

/**
 * Created by kropla on 12.11.16.
 */
@Component("xmlWriter")
public class XmlSentenceWriter extends SentenceFileWriter implements SentenceWriter {

    static final String FILE_NAME = "out.xml";
    private static final String NODE_NAME_OPEN_SENTENCE = "<sentence>";
    private static final String NODE_NAME_CLOSE_SENTENCE = "</sentence>";
    private static final String NODE_NAME_OPEN_WORD = "<word>";
    private static final String NODE_NAME_CLOSE_WORD = "</word>";
    private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>";
    private static final String FOOTER = "</text>";

    XmlSentenceWriter() {
        super(FILE_NAME, HEADER, FOOTER);
    }

    @Override
    public String formatLineSentence(Sentence sentence) {
        StringBuilder line = new StringBuilder(NODE_NAME_OPEN_SENTENCE);

        for (String word : sentence.getWords()){
            line.append(NODE_NAME_OPEN_WORD).append(word).append(NODE_NAME_CLOSE_WORD);
        }
        line.append(NODE_NAME_CLOSE_SENTENCE);
        return line.toString();
    }

}
