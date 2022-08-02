package com.belatry.controller;

import com.belatry.model.Dictionary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    private Dictionary dictionary;

    @ModelAttribute("dictionary")
    public Dictionary getDictionary() {
        return dictionary;
    }

    public DictionaryController(@Qualifier("allWordsDictionary") Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @GetMapping("/all")
    public String showDictionary() {
        return "/dictionary";
    }

    @GetMapping("/{lineNumber}")
    public String getWordFromLine(@PathVariable("lineNumber") int lineNumber, Model model) {
        model.addAttribute("wordFromLine", dictionary.getWord(lineNumber).get());
        return "/dictionary";
    }

    @PostMapping("/add")
    public String addWord(@RequestParam("word") String word) {
        dictionary.add(word);

        return "redirect:/dictionary/all";
    }

    @DeleteMapping("/delete")
    public String deleteWord(@RequestParam("word") String word) {
        dictionary.delete(word);
        return "redirect:/dictionary/all";
    }
}
