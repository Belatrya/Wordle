package model;

import static java.io.File.separatorChar;

/**
 * Describes the types of used dictionary with the path where to find them.
 */
public enum DictionaryType {
    HIDDEN_WORDS(String.format(".%sout%sresources%shiddenWords.txt", separatorChar, separatorChar, separatorChar)),
    ALL_WORDS(String.format(".%sout%sresources%srussian5letterwords.txt", separatorChar, separatorChar, separatorChar));
    private String path;

    DictionaryType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}