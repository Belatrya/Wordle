package main.java.checkers;

import main.java.base.Word;

public class Checker {
    private Word word;

    public Checker(String word, int ruleWordLength) {
        if (word.length() == ruleWordLength) {
            this.word = new Word(word);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Word getWord() {
        return word;
    }
}
