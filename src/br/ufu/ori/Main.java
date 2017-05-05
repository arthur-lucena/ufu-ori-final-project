package br.ufu.ori;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Word> words = new HashMap<String, Word>();

            Files.newBufferedReader(Paths.get("hinoDaBandeira.txt")).lines().forEach(l -> countWords(l, words));
            Files.newBufferedReader(Paths.get("hinoDaIndependencia.txt")).lines().forEach(l -> countWords(l, words));
            Files.newBufferedReader(Paths.get("hinonacional.txt")).lines().forEach(l -> countWords(l, words));

            List<Word> peopleByAge = new ArrayList<Word>(words.values());

            Collections.sort(peopleByAge, new Comparator<Word>() {
                public int compare(Word o1, Word o2) {
                    return o2.getCount() - o1.getCount();
                }
            });

            for (Word w : peopleByAge) {
                System.out.println(w.getWord() + "  " + w.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void countWords(String s, Map<String, Word> words) {
        String[] arrayAux = s.replaceAll("\\p{P}", " ").split("\\s+");
        Word auxWord;

        for (String aux : arrayAux) {
            if (aux.length() > 3) {
                String word = aux.toLowerCase();

                if (words.containsKey(word)) {
                    auxWord = (Word)words.get(word);
                    auxWord.increment();
                    words.replace(word, auxWord);
                } else {
                    auxWord = new Word(word);
                    auxWord.increment();
                    words.put(word, auxWord);
                }
            }
        }
    }
}
