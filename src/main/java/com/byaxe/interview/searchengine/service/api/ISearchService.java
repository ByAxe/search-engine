package com.byaxe.interview.searchengine.service.api;

import com.byaxe.interview.searchengine.dto.DocumentReference;
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

    @NonNull List<DocumentReference> getAllDocuments(@NonNull String query);

    @NonNull List<DocumentReference> getFirstNDocuments(@NonNull String query, long n);

    DocumentReference getFirstDocument(@NonNull String query);
}
