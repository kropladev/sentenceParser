package com.kropla.model;

import java.util.List;

/**
 * Created by kropla on 11.11.16.
 */
public class Sentence implements Comparable<Sentence>{
    private int id;
    private List<String> words;

    public Sentence(int id, List<String> words) {
        this.id = id;
        this.words = words;
    }

    @Override
    public int hashCode() {
        return id * words.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        final Sentence sentence = (Sentence) o;

        if (this.id == sentence.id) {
            if (this.words != null ? this.words.equals(sentence.words) : sentence.words == null) return true;
        }

        return false;
    }

    public List<String> getWords() {
        return words;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Sentence sentence) {
        return this.id - sentence.id;
    }

    @Override
    public String toString(){
        return "id:"+id+" |"+words.toString();
    }

}