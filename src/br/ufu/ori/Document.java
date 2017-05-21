package br.ufu.ori;

import java.util.*;

/**
 * Created by arthur on 5/7/17.
 */
public class Document {
    private Map<String, Word> words;
    private Double norma;

    public Document() {
        words = new HashMap<String, Word>();
    }

    public Map<String, Word> getWords() {
        return words;
    }

    public Double getNorma() {
        return norma;
    }

    public void setNorma(Double norma) {
        this.norma = norma;
    }

    public void calcTFIDF() throws Exception {
        for (String s : this.getWords().keySet()) {
            calcTFIDF(s);
        }
    }

    private void calcTFIDF (String word) {
        this.words.get(word).calcTFIDF();
    }

    @Override
    public String toString() {
        List<Word> listWords = new ArrayList<>(this.getWords().values());
        String ret = "";

        Collections.sort(listWords, new Comparator<Word>() {
            public int compare(Word o1, Word o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });

        for (Word w : listWords) {
            ret += w.toString();
        }

        return ret;
    }
}
