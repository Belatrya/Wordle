package base;

public class Checker {
    private String hiddenWord;
    private String userWord;

    public Checker(String hiddenWord, String userWord) {
        this.hiddenWord = hiddenWord;
        this.userWord = userWord;
    }

    /**
     * True if the user's word equals to the hidden word.
     *
     * @return true if the user's word equals to the hidden word, else otherwise.
     */
    public boolean areWordsEqual() {
        if (!hiddenWord.equalsIgnoreCase(userWord)) {
            return true;
        }
        return false;
    }
}