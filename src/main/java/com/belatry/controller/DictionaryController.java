package com.belatry.controller;

import com.belatry.model.Dictionary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController(@Qualifier("allWordsDictionary") Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @GetMapping("/all")
    public List<String> showDictionary() {
        return dictionary.getAllWords();
    }

    @GetMapping(value = "/{lineNumber}",
            produces = "text/plain;charset=UTF-8")
    public String getWordFromLine(@PathVariable("lineNumber") int lineNumber) {
        return dictionary.getWord(lineNumber).get();
    }

    @PostMapping("/add")
    public void addWord(@RequestParam("word") String word) {
        dictionary.add(word);
    }

    @DeleteMapping("/delete")
    public void deleteWord(@RequestParam("word") String word) {
        dictionary.delete(word);
    }

    @PutMapping("/{word}/edit")
    public void editWord(@PathVariable("word") String word, @RequestParam("editedWord") String editedWord) {
        if (dictionary.isExists(word)) {
            dictionary.add(editedWord);
            dictionary.delete(word);
        }
    }
}
