package com.nordea.assignment.model;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by kropla on 11.11.16.
 */
@XmlRootElement
public class Sentence implements Comparable<Sentence>{
    private int id;

    private List<String> words;

    Sentence(int id, List<String> words) {
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


    @XmlElement(name="word")
    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setId(int id) {
        this.id = id;
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

}