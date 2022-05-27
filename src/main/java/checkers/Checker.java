package main.java.checkers;

import main.java.base.Word;

public class Checker {
    private Word word;

    public Checker(String word) {
        this.word = new Word(word);
    }

    public Word getWord() {
        return word;
    }

    public boolean isExistingWord() {

        return true;
    }

    public boolean isLettersCountCorrect(int ruleWordLength) {
        if (word.getLettersCount() == ruleWordLength) {
            return true;
        }
        return false;
    }
}
