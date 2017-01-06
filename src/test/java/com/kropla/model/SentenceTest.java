package com.kropla.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kropla on 17.11.16.
 */
public class SentenceTest {

    @Test
    public void hashCodeTest(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");

        Sentence sentence = new Sentence(1, sentences);

        Assert.assertEquals(sentences.hashCode(), sentence.hashCode());
    }


    @Test
    public void shouldTwoSentencesBeEqual(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");
        Sentence sentence1 = new Sentence(1,sentences);
        Sentence sentence2 = new Sentence(1,sentences);


        Assert.assertTrue(sentence1.equals(sentence2));
    }

    @Test
    public void shouldTwoSentencesBeNonEqual(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");
        Sentence sentence1 = new Sentence(1,sentences);
        Sentence sentence2 = new Sentence(2,sentences);


        Assert.assertTrue(!sentence1.equals(sentence2));
    }

    @Test
    public void shouldTwoSentencesBeNonEqual2(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");
        List<String> sentences2 = Arrays.asList("what","he","shouted","was");
        Sentence sentence1 = new Sentence(1,sentences);
        Sentence sentence2 = new Sentence(1,sentences2);


        Assert.assertTrue(!sentence1.equals(sentence2));
    }

    @Test
    public void shouldGetWords(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");

        Sentence sentence1 = new Sentence(1,sentences);
        List<String> sentencesOut = sentence1.getWords();
        Assert.assertTrue(sentence1.getWords().equals(sentencesOut));
    }


    @Test
    public void shouldGeId(){
        List<String> sentences = Arrays.asList("What","he","shouted","was");

        Sentence sentence1 = new Sentence(1,sentences);
        int id  = sentence1.getId();

        Assert.assertTrue(id == 1);
    }

    @Test
    public void shouldCompareTwoSentencesAsEquals(){
        List<String> sentences1 = Arrays.asList("What","he","shouted","was");
        List<String> sentences2 = Arrays.asList("What","he","shouted","was");

        Sentence sentence1 = new Sentence(1,sentences1);
        Sentence sentence2 = new Sentence(1,sentences2);

        Assert.assertTrue(sentence1.compareTo(sentence2) == 0);
    }


    @Test
    public void shouldCompareTwoSentencesAsFirstBigger(){
        List<String> sentences1 = Arrays.asList("What","he","shouted","was");
        List<String> sentences2 = Arrays.asList("What","he","shouted","was");

        Sentence sentence1 = new Sentence(1,sentences1);
        Sentence sentence2 = new Sentence(2,sentences2);

        int test = sentence1.compareTo(sentence2);
        Assert.assertTrue(sentence1.compareTo(sentence2) == -1);
    }

    @Test
    public void shouldCompareTwoSentencesAsFirstSmaller(){
        List<String> sentences1 = Arrays.asList("What","he","shouted","was");
        List<String> sentences2 = Arrays.asList("What","he","shouted","was");

        Sentence sentence1 = new Sentence(2,sentences1);
        Sentence sentence2 = new Sentence(1,sentences2);

        Assert.assertTrue(sentence1.compareTo(sentence2) == 1);
    }

}
