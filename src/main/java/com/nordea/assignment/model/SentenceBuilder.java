package com.nordea.assignment.model;

import java.util.List;

/**
 * Created by kropla on 12.11.16.
 */
public class SentenceBuilder {
        private static int counter = 0;

        private List<String> words;

        public  SentenceBuilder words(List<String> wordList) {
            this.words = wordList;
            return this;
        }

        public Sentence build(){
            return new Sentence(counter++,this.words);
        }
}
