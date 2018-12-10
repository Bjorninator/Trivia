package com.example.reijn.trivia;

public class question {
    String category,typeq,difficulty,question,correct,incorrect1, incorrect2, incorrect3;

    public question(String category, String typeq, String difficulty, String question, String correct, String incorrect) {
        String incorrectiets = incorrect;
        incorrectiets = incorrectiets.replace("\"", "");
        incorrectiets = incorrectiets.replace("[", "");
        incorrectiets = incorrectiets.replace("]", "").replace("&#039;","\'").replace("&quot;","\"");
        String[] separated = incorrectiets.split(",");
        System.out.println(separated[2]);
        this.category = category;
        this.typeq = typeq;
        this.difficulty = difficulty;
        this.question = question.replace("&#039;","\'").replace("&quot;","\"");
        this.correct = correct;
        this.incorrect1 = separated[0];
        this.incorrect2 = separated[1];
        this.incorrect3 = separated[2];
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTypeq(String typeq) {
        this.typeq = typeq;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setIncorrect1(String incorrect1) {
        this.incorrect1 = incorrect1;
    }

    public void setIncorrect2(String incorrect2) {
        this.incorrect2 = incorrect2;
    }

    public void setIncorrect3(String incorrect3) {
        this.incorrect3 = incorrect3;
    }

    public String getIncorrect1() {
        return incorrect1;
    }

    public String getIncorrect2() {
        return incorrect2;
    }

    public String getIncorrect3() {
        return incorrect3;
    }

    public String getCategory() {
        return category;
    }

    public String getTypeq() {
        return typeq;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correct;
    }

}
