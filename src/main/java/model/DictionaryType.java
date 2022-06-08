package model;

/**
 * Describes the types of used dictionary with the path where to find them.
 */
public enum DictionaryType {
    HIDDEN_WORDS(".\\out\\resources\\hiddenWords.txt"),
    ALL_WORDS(".\\out\\resources\\russian5letterwords.txt");

    private String path;

    DictionaryType (String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}