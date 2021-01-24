package com.byaxe.interview.searchengine.service.api;

import com.byaxe.interview.searchengine.dto.Word;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ASearchService implements ISearchService {

    /**
     * Cut every word except root of the word
     *
     * @param query query, split into {@link Word}
     * @return list of normalized words
     */
    protected List<Word> normalizeQuery(List<Word> query) {
        return query.stream()
                .map(this::normalizeWord)
                .collect(Collectors.toList());
    }

    /**
     * Cut everything except the root of the word
     *
     * @param originalWord source word
     * @return normalized word
     */
    protected abstract Word normalizeWord(Word originalWord);

    protected abstract List<Word> splitQueryToListOfWords(String query);

}
