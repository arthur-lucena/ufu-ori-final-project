package br.ufu.ori;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a sua consulta separada por virgula:  ");
            String q = sc.next().toLowerCase();

            q = q.replaceAll("\\s+","");
            List<String> qList = new ArrayList<String>();


            for (String s : Arrays.asList(q.split(","))) {
                if (!s.isEmpty()) {
                    qList.add(s);
                }
            }

            for (String s : qList) {
                System.out.println(s);
            }
//            fsdfsd,,fsd,f,sfs,df,sd,fsd, f ,fsfa, ,f ,sda,f

            letraABCD();
            // letraE();
//            multipleDocuments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void multipleDocuments() throws Exception {
        List<String> nameDocuments = new ArrayList<String>();
        nameDocuments.add("document1");
        nameDocuments.add("document2");
        nameDocuments.add("document3");
        nameDocuments.add("document4");

        List<Document> documents = new ArrayList<Document>();

        // Document d;
        for (String nameDocument : nameDocuments) {
            Document d = new Document();

            Files.newBufferedReader(Paths.get(nameDocument)).lines().forEach(l -> countWords(l, d.getWords(), true));

            documents.add(d);
        }

        calc(documents);

        String nameFile = String.valueOf(documents.hashCode());
        new File(nameFile).delete();
        PrintWriter writer = new PrintWriter(nameFile, "UTF-8");

        System.out.println("termo; fi1; fi2; fi3; fi4; tf1; tf2; tf3; tf4; idf; tfidf1; tfidf2; tfidf3; tfidf4; norma;");
        writer.println("termo; fi1; fi2; fi3; fi4; tf1; tf2; tf3; tf4; idf; tfidf1; tfidf2; tfidf3; tfidf4; norma;");

        for (String key : documents.get(0).getWords().keySet()) {
            Document d1 = documents.get(0);
            Document d2 = documents.get(1);
            Document d3 = documents.get(2);
            Document d4 = documents.get(3);

            Word w1 = d1.getWords().get(key);
            Word w2 = d2.getWords().get(key);
            Word w3 = d3.getWords().get(key);
            Word w4 = d4.getWords().get(key);

            System.out.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma(), d4.getNorma()));
            writer.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma(), d4.getNorma()));

        }

        writer.close();


    }


    private static void calc(List<Document> documents) throws Exception {
        for (Document dOrigin : documents) {
            for (int c = documents.size() - 1; c >= 0; c--) {
                Document dDestiny = documents.get(c);

                equalizeDocuments(dOrigin, dDestiny);
            }
        }

        Integer totalDocuments = documents.size();
        Integer totalWordsRepeatedInDocuments;

        if (documents.size() < 1) {
            throw new Exception("Documents has to be a least 1 document");
        }

        Integer totalWordsInAllDocuments = documents.get(0).getWords().size();

//        for () {
//
//        }

        Double normaD1 = 0d;
        Double normaD2 = 0d;
        Double normaD3 = 0d;
        Double normaD4 = 0d;
//
//        for (String key : d1.getWords().keySet()) {
//            facefaceO = 0;
//
//            Word wordD1 = d1.getWords().get(key);
//            Word wordD2 = d2.getWords().get(key);
//            Word wordD3 = d3.getWords().get(key);
//            Word wordD4 = d4.getWords().get(key);
//
//            if (wordD1.getCount() > 0) {
//                totalWordsRepeatedInDocuments++;
//            }
//
//            if (wordD2.getCount() > 0) {
//                totalWordsRepeatedInDocuments++;
//            }
//
//            if (wordD3.getCount() > 0) {
//                totalWordsRepeatedInDocuments++;
//            }
//
//            if (wordD4.getCount() > 0) {
//                totalWordsRepeatedInDocuments++;
//            }
//
//            wordD1.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
//            wordD1.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
//            wordD1.setTotalDocuments(totalDocuments);
//            wordD1.calcTFIDF();
//
//            wordD2.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
//            wordD2.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
//            wordD2.setTotalDocuments(totalDocuments);
//            wordD2.calcTFIDF();
//
//            wordD3.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
//            wordD3.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
//            wordD3.setTotalDocuments(totalDocuments);
//            wordD3.calcTFIDF();
//
//            wordD4.setTotalWordsRepeatedInDocuments(totalWordsRepeatedInDocuments);
//            wordD4.setTotalWordsInAllDocuments(totalWordsInAllDocuments);
//            wordD4.setTotalDocuments(totalDocuments);
//            wordD4.calcTFIDF();
//
//            normaD1 += Math.pow(wordD1.getTFIDF(), 2D);
//            normaD2 += Math.pow(wordD2.getTFIDF(), 2D);
//            normaD3 += Math.pow(wordD3.getTFIDF(), 2D);
//            normaD4 += Math.pow(wordD4.getTFIDF(), 2D);
//
//        }
//
//        d1.setNorma(Math.sqrt(normaD1));
//        d2.setNorma(Math.sqrt(normaD2));
//        d3.setNorma(Math.sqrt(normaD3));
//        d4.setNorma(Math.sqrt(normaD4));
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

            System.out.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma(), d4.getNorma()));
            writer.println(String.format("%s; %d; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(), w4.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w4.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), w4.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma(), d4.getNorma()));

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

        calc(d1, d2, d3);

        new File("d1d2d3_analysis").delete();
        PrintWriter writer = new PrintWriter("d1d2d3_analysis", "UTF-8");

        System.out.println("termo; fi1; fi2; fi3; tf1; tf2; tf3; idf; tfidf1; tfidf2; tfidf3; normad1; normad2; normad3;");
        writer.println("termo; fi1; fi2; fi3; tf1; tf2; tf3; idf; tfidf1; tfidf2; tfidf3; normad1; normad2; normad3;");

        for (String key : d1.getWords().keySet()) {
            Word w1 = d1.getWords().get(key);
            Word w2 = d2.getWords().get(key);
            Word w3 = d3.getWords().get(key);

            System.out.println(String.format("%s; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma()));
            writer.println(String.format("%s; %d; %d; %d; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f; %.3f;", key, w1.getCount(), w2.getCount(), w3.getCount(),
                    w1.getTF(), w2.getTF(), w3.getTF(), w1.getIDF(), w1.getTFIDF(), w2.getTFIDF(), w3.getTFIDF(), d1.getNorma(), d2.getNorma(), d3.getNorma()));

        }

        writer.close();
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

        Double normaD1 = 0d;
        Double normaD2 = 0d;
        Double normaD3 = 0d;
        Double normaD4 = 0d;

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

            normaD1 += Math.pow(wordD1.getTFIDF(), 2D);
            normaD2 += Math.pow(wordD2.getTFIDF(), 2D);
            normaD3 += Math.pow(wordD3.getTFIDF(), 2D);
            normaD4 += Math.pow(wordD4.getTFIDF(), 2D);

        }

        d1.setNorma(Math.sqrt(normaD1));
        d2.setNorma(Math.sqrt(normaD2));
        d3.setNorma(Math.sqrt(normaD3));
        d4.setNorma(Math.sqrt(normaD4));
    }

    private static void calc(Document d1, Document d2, Document d3) {
        equalizeDocuments(d3, d1);
        equalizeDocuments(d2, d1);
        equalizeDocuments(d1, d2);
        equalizeDocuments(d2, d3);

        Integer totalDocuments = 3;
        Integer totalWordsRepeatedInDocuments;
        Integer totalWordsInAllDocuments = d1.getWords().size();

        Double normaD1 = 0d;
        Double normaD2 = 0d;
        Double normaD3 = 0d;

        for (String key : d1.getWords().keySet()) {
            totalWordsRepeatedInDocuments = 0;

            Word wordD1 = d1.getWords().get(key);
            Word wordD2 = d2.getWords().get(key);
            Word wordD3 = d3.getWords().get(key);

            if (wordD1.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            if (wordD2.getCount() > 0) {
                totalWordsRepeatedInDocuments++;
            }

            if (wordD3.getCount() > 0) {
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

            normaD1 += Math.pow(wordD1.getTFIDF(), 2D);
            normaD2 += Math.pow(wordD2.getTFIDF(), 2D);
            normaD3 += Math.pow(wordD3.getTFIDF(), 2D);

        }

        d1.setNorma(Math.sqrt(normaD1));
        d2.setNorma(Math.sqrt(normaD2));
        d3.setNorma(Math.sqrt(normaD3));
    }

    private static void equalizeDocuments(Document originDocument, Document destinyDocument) {
        for (String key : originDocument.getWords().keySet()) {
            if (!destinyDocument.getWords().containsKey(key)) {
                destinyDocument.getWords().put(key, new Word(key));
            }
        }
    }
}
