package com.byaxe.interview.searchengine.repository.api;

import com.byaxe.interview.searchengine.dto.Document;

import java.util.List;

public interface ISearchRepository {
    List<Document> getFirstNDocuments(List<String> query, long n);
}
