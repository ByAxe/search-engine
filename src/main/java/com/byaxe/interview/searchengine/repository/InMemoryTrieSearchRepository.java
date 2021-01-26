package com.byaxe.interview.searchengine.repository;

import com.byaxe.interview.searchengine.core.SearchTrie;
import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.dto.DocumentReference;
import com.byaxe.interview.searchengine.dto.Word;
import com.byaxe.interview.searchengine.repository.api.ISearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemoryTrieSearchRepository implements ISearchRepository {

    private final SearchTrie searchTrie;

    public InMemoryTrieSearchRepository(SearchTrie searchTrie) {
        this.searchTrie = searchTrie;
    }

    @Override
    public List<DocumentReference> getFirstNDocuments(List<Word> query, long n) {
        // Create cache and check in it

        // Get all languages those are in query
        final Set<Language> languageSet = query.stream()
                .map(Word::getLanguage)
                .collect(Collectors.toSet());

        final List<DocumentReference> documentReferences = searchTrie.findAndFilter(query, languageSet);

        if (n == -1) return documentReferences;
        else return documentReferences.stream().limit(n).collect(Collectors.toList());
    }


}
