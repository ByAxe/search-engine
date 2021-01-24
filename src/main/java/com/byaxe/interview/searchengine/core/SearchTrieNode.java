package com.byaxe.interview.searchengine.core;

import lombok.Data;

import java.util.*;

@Data
public class SearchTrieNode {
    private Map<String, SearchTrieNode> children = new HashMap<>();
    private String content;
    private boolean endOfDocument;

    /**
     * Documents, those contain current word
     */
    private Set<UUID> documentIds = new HashSet<>();

    public SearchTrieNode() {
    }

    public SearchTrieNode(String content, UUID documentId) {
        this.content = content;
        documentIds.add(documentId);
    }
}
