package com.byaxe.interview.searchengine.repository.api;

import com.byaxe.interview.searchengine.dto.DocumentReference;

import java.util.List;

public interface ISearchRepository {
    List<DocumentReference> getFirstNDocuments(List<String> query, long n);
}
