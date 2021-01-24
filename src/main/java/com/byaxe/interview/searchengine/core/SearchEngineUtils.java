package com.byaxe.interview.searchengine.core;

import com.byaxe.interview.searchengine.dto.Word;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public final class SearchEngineUtils {

    /**
     * Extracts content only from {@link Word}
     * And converts whole list to list of Strings
     *
     * @param wordList list of DTOs representing Word with metadata
     * @return list of plain {@link String} with words content
     */
    public static List<String> listOfWordsToListOfStrings(@NonNull List<Word> wordList) {
        return wordList.stream()
                .map(Word::getContent)
                .collect(Collectors.toList());
    }
}
