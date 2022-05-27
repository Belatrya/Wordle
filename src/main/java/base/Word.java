package main.java.base;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final List<Letter> word = new ArrayList<>();
    private int lettersCount;

    public Word(String word) {
        for (char let : word.toCharArray()) {
            Letter letter = new Letter(let);
            this.word.add(letter);
        }
    }

    public List<Letter> getWord() {
        return word;
    }

    public int getLettersCount() {
        return word.size();
    }
}
