package com.byaxe.interview.searchengine.core;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.dto.DocumentReference;
import com.byaxe.interview.searchengine.dto.IndexedDocument;
import com.byaxe.interview.searchengine.dto.Word;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.*;

/**
 * Type of trees where each node connected to N children
 * And each downwards path (from root+1 to leaf) represents document
 */
@EqualsAndHashCode
public class SearchTrie {
    private final SearchTrieNode root = new SearchTrieNode();

    /**
     * Add document to Trie
     * O(n) complexity operation
     *
     * @param document indexed document with list of words and other metadata info
     */
    public void insert(IndexedDocument document) {
        SearchTrieNode currentNode = root;
        UUID id = document.getId();

        document.getWords()
                .forEach(word -> currentNode
                        .getChildren()
                        // todo merge SearchTrieNodes
                        .putIfAbsent(word, new SearchTrieNode(word, id)));

        currentNode.setEndOfDocument(true);
    }

    /**
     * Returns all found document references
     *
     * @param query
     * @return
     */
    public List<DocumentReference> find(List<Word> query) {
        SearchTrieNode currentNode = root;

        final Map<UUID, DocumentReference> documentReferenceMap = new HashMap<>();

        for (Word word : query) {
            SearchTrieNode trieNode = currentNode.getChildren().get(word.getContent());
            if (trieNode == null) continue;

            trieNode.getDocumentIds()
                    .forEach(id -> {
                        final DocumentReference newValue = DocumentReference.builder()
                                .id(id)
                                .coincidedContent(Arrays.asList(trieNode.getContent()))
//                                .startMatch()
//                                .endMatch()
                                .build();

                        documentReferenceMap.merge(id, newValue, this::mergeDocumentReferences);
                    });
        }

        // TODO Return all paths sorted desc by longest sequences of coinciding words

        return new ArrayList<>(documentReferenceMap.values());
    }

    public void delete(IndexedDocument document) {
        // TODO implement deletion
    }

    private DocumentReference mergeDocumentReferences(@NonNull DocumentReference presentValue,
                                                      @NonNull DocumentReference newValue) {
        presentValue.setEndMatch(newValue.getEndMatch());
        presentValue.getCoincidedContent().addAll(newValue.getCoincidedContent());

        return presentValue;

    }

    public List<DocumentReference> findAndFilter(List<Word> query, Set<Language> languageSet) {

        // TODO Check via a "Bloom Filter" documents
        //  and filter out those not containing needed languages

        return find(query);
    }
}
