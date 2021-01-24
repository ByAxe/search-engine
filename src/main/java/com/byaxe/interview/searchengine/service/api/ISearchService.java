package com.byaxe.interview.searchengine.service.api;

import com.byaxe.interview.searchengine.dto.Document;
import com.byaxe.interview.searchengine.dto.Word;
import lombok.NonNull;

import java.util.List;

public interface ISearchService {
    /**
     * Split incoming query into list of meaningful words
     *
     * @param query incoming query
     * @return List of meaningful words
     */
    @NonNull List<Word> splitQuery(@NonNull String query);

    @NonNull List<Document> getAllDocuments(@NonNull String query);

    @NonNull List<Document> getFirstNDocuments(@NonNull String query, long n);

    Document getFirstDocument(@NonNull String query);
}
