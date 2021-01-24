package com.byaxe.interview.searchengine.dto.api;

import com.byaxe.interview.searchengine.core.enums.WordsDirection;

import java.util.Set;

public interface ILanguageDTO {
    String getTitle();

    Set<String> getUselessSymbols();

    Set<String> getPrepositions();

    WordsDirection getWordsDirection();
}
