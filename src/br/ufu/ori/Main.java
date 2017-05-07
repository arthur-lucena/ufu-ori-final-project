package br.ufu.ori;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            letraABCD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void letraABCD() throws Exception{
        Document d1 = new Document();
        Document d2 = new Document();
        Document d3 = new Document();
        Document d4 = new Document();

        Files.newBufferedReader(Paths.get("document1")).lines().forEach(l -> countWords(l, d1.getWords(), true));
        Files.newBufferedReader(Paths.get("document2")).lines().forEach(l -> countWords(l, d2.getWords(), true));
        Files.newBufferedReader(Paths.get("document3")).lines().forEach(l -> countWords(l, d3.getWords(), true));
        Files.newBufferedReader(Paths.get("document4")).lines().forEach(l -> countWords(l, d4.getWords(), true));

        calc(d1, d2, d3, d4);

        new File("d1d2d3d4_analysis").delete();
        PrintWriter writer = new PrintWriter("d1d2d3d4_analysis", "UTF-8");

        System.out.println("termo; fi1; fi2; fi3; fi4; tf1; tf2; tf3; tf4; idf; tfidf1; tfidf2; tfidf3; tfidf4; norma;");
        writer.println("termo; fi1; fi2; fi3; fi4; tf1; tf2; tf3; tf4; idf; tfidf1; tfidf2; tfidf3; tfidf4; norma;");

        for (String key : d1.getWords().keySet()) {
            Word w1 = d1.getWords().get(key);
            Word w2 = d2.getWords().get(key);
            Word w3 = d3.getWords().get(key);
            Word w4 = d4.getWords().get(key);

            System.out.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), w1.getNorma()));
            writer.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), w1.getNorma()));

        }

        writer.close();
    }

    private static void letraE() throws Exception {
        Document d1 = new Document();
        Document d2 = new Document();
        Document d3 = new Document();

        Files.newBufferedReader(Paths.get("hinoDaBandeira.txt")).lines().forEach(l -> countWords(l, d1.getWords(), false));
        Files.newBufferedReader(Paths.get("hinoDaIndependencia.txt")).lines().forEach(l -> countWords(l, d2.getWords(), false));
        Files.newBufferedReader(Paths.get("hinonacional.txt")).lines().forEach(l -> countWords(l, d3.getWords(), false));
    }

    private static void countWords(String s, Map<String, Word> words, Boolean lettersByPass) {
        String[] arrayAux = s.replaceAll("\\p{P}", " ").split("\\s+");
        Word auxWord;

        for (String aux : arrayAux) {
            if (lettersByPass || aux.length() > 3) {
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

    private static void calc(Document d1, Document d2, Document d3, Document d4) {
        equalizeDocuments(d4, d1);
        equalizeDocuments(d3, d1);
        equalizeDocuments(d2, d1);
        equalizeDocuments(d1, d2);
        equalizeDocuments(d2, d3);
        equalizeDocuments(d3, d4);

        Integer totalDocuments = 4;
        Integer totalWordsRepeatedInDocuments;
        Integer totalWordsInAllDocuments = d1.getWords().size();

        Double norma;

        for (String key : d1.getWords().keySet()) {
            totalWordsRepeatedInDocuments = 0;

            Word wordD1 = d1.getWords().get(key);
            Word wordD2 = d2.getWords().get(key);
            Word wordD3 = d3.getWords().get(key);
            Word wordD4 = d4.getWords().get(key);

            if (wordD1.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            if (wordD2.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            if (wordD3.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            if (wordD4.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            wordD1.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
            wordD1.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
            wordD1.setTotalDocuments(totalDocuments);
            wordD1.calcTFIDF();

            wordD2.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
            wordD2.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
            wordD2.setTotalDocuments(totalDocuments);
            wordD2.calcTFIDF();

            wordD3.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
            wordD3.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
            wordD3.setTotalDocuments(totalDocuments);
            wordD3.calcTFIDF();

            wordD4.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
            wordD4.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
            wordD4.setTotalDocuments(totalDocuments);
            wordD4.calcTFIDF();

            norma = Math.sqrt(Math.pow(wordD1.getTFIDF(), 2D) + Math.pow(wordD2.getTFIDF(), 2D) + Math.pow(wordD3.getTFIDF(), 2D) + Math.pow(wordD4.getTFIDF(), 2D));

            wordD1.setNorma(norma);
            wordD2.setNorma(norma);
            wordD3.setNorma(norma);
            wordD4.setNorma(norma);
        }
    }

    private static void equalizeDocuments(Document originDocument, Document destinyDocument) {
        for (String key : originDocument.getWords().keySet()) {
            if (!destinyDocument.getWords().containsKey(key)) {
                destinyDocument.getWords().put(key, new Word(key));
            }
        }
    }


}
