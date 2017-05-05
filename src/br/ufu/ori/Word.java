package br.ufu.ori;

/**
 * Created by arthur on 4/25/17.
 */
public class Word {
    private String word;
    private Integer count;

    public Word(String word) {
        this.word = word;
        this.count = 0;
    }
    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

}
