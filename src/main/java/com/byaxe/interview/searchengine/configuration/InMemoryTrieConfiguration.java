package com.byaxe.interview.searchengine.configuration;

import com.byaxe.interview.searchengine.core.SearchTrie;
import com.byaxe.interview.searchengine.dto.IndexedDocument;
import com.byaxe.interview.searchengine.dto.RawDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InMemoryTrieConfiguration {

    @Bean
    public SearchTrie searchTrie(List<IndexedDocument> documentList) {
        final SearchTrie searchTrie = new SearchTrie();

        documentList.forEach(searchTrie::insert);

        return searchTrie;
    }

    @Bean
    public List<IndexedDocument> documentList(List<RawDocument> rawDocuments) {
        // TODO convert raw documents to indexed documents
        return new ArrayList<>();
    }
}
