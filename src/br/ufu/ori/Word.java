package br.ufu.ori;

/**
 * Created by arthur on 4/25/17.
 */
public class Word {
    private String word;
    private Integer count;
    private Double TF;
    private Double IDF;
    private Double TFIDF;
    private Integer totalDocuments;
    private Integer totalWordsRepeatedInDocuments;
    private Integer totalWordsInAllDocuments;

    public Word(String word) {
        this.word = word;
        this.count = 0;
        this.TF = -1D;
        this.IDF = -1D;
        this.TFIDF = -1D;
        this.totalDocuments = -1;
        this.totalWordsRepeatedInDocuments = 1;
        this.totalWordsInAllDocuments = -1;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTF() {
        return TF;
    }

    public Double getIDF() {
        return IDF;
    }

    public Double getTFIDF() {
        return TFIDF;
    }

    public Integer getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(Integer totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    public Integer getTotalWordsRepeatedInDocuments() {
        return totalWordsRepeatedInDocuments;
    }

    public void setTotalWordsRepeatedInDocuments(Integer totalWordsRepeatedInDocuments) {
        this.totalWordsRepeatedInDocuments = totalWordsRepeatedInDocuments;
    }

    public Integer getTotalWordsInAllDocuments() {
        return totalWordsInAllDocuments;
    }

    public void setTotalWordsInAllDocuments(Integer totalWordsInAllDocuments) {
        this.totalWordsInAllDocuments = totalWordsInAllDocuments;
    }

    public void increment() {
        count++;
    }

    private void calcTF() {
        if (count > 0) {
            this.TF = 1 + Math.log(Double.valueOf(count)) / Math.log(2);
        } else {
            this.TF = 0D;
        }
    }

    private void calcIDF() {
         this.IDF = Math.log(Double.valueOf(totalDocuments.toString()) / Double.valueOf(totalWordsRepeatedInDocuments.toString())) / Math.log(2);
    }

    public void calcTFIDF() {
        calcTF();
        calcIDF();
        this.TFIDF = this.TF * this.IDF;
    }



    @Override
    public String toString() {
        return String.format("%s: %d; TF: %f; IDF: %f; TF-IDF: %f; totalDoc: %d; totalRepeatDoc: %d; totalWordsDoc: %d\n",
                this.word, this.count, this.TF, this.IDF, this.TFIDF, this.totalDocuments, this.totalWordsRepeatedInDocuments, this.totalWordsInAllDocuments);

    }

}
