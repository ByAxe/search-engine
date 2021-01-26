package com.byaxe.interview.searchengine.repository.api;

import com.byaxe.interview.searchengine.dto.DocumentReference;
import com.byaxe.interview.searchengine.dto.Word;

import java.util.List;

public interface ISearchRepository {
    List<DocumentReference> getFirstNDocuments(List<Word> query, long n);
}
