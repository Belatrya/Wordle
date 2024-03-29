package com.belatry.controller;

import com.belatry.model.Dictionary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Represents the operations for the words in the "all words" dictionary.
 */
@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController(@Qualifier("allWordsDictionary") Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Operation(summary = "Returns all words from the dictionary")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "all words from the dictionary",
            content = @Content(schema = @Schema(implementation = List.class)))})
    @GetMapping("/all-words")
    public ResponseEntity<?> showDictionary() {
        return ResponseEntity.ok(dictionary.getAllWords());
    }

    @Operation(summary = "The word from the line by number")
    @GetMapping(value = "/{lineNumber}",
            produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> getWordFromLine(@PathVariable int lineNumber) {
        if (dictionary.getWord(lineNumber).isPresent()) {
            return ResponseEntity.ok(dictionary.getWord(lineNumber).get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Adds the word to the end of the file if the word doesn't exist yet")
    @PostMapping("/add")
    public ResponseEntity<?> addWord(@RequestParam String word) {
        dictionary.add(word);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deletes the word from the dictionary if it exists")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWord(@RequestParam String word) {
        dictionary.delete(word);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deleted the old word and added the new one instead of it")
    @PutMapping("/{oldValueWord}/edit")
    public ResponseEntity<?> editWord(
            @Parameter(description = "the old word value, should exist in the dictionary to be edited")
            @PathVariable String oldValueWord,
            @Parameter(description = "the new value for the word") @RequestParam String newValueWord) {
        if (dictionary.isExists(oldValueWord)) {
            dictionary.add(newValueWord);
            dictionary.delete(oldValueWord);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
