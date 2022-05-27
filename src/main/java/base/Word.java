package main.java.base;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final List<Letter> word = new ArrayList<>();

    public Word(String word) {
        for (char let : word.toUpperCase().toCharArray()) {
            Letter letter = new Letter(let);
            this.word.add(letter);
        }
    }

    public List<Letter> getWord() {
        return word;
    }
}
