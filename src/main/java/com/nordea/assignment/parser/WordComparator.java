package com.nordea.assignment.parser;

import java.util.Comparator;

/**
 * Created by kropla on 12.11.16.
 */
public class WordComparator implements Comparator<String> {
    @Override
    public int compare(String s, String t1) {
        if (s.equalsIgnoreCase(t1)){
            return t1.compareTo(s);
        }
        return s.compareToIgnoreCase(t1);
    }
}
